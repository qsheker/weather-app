package org.qsheker.weatherapplication.strategy.concreateStrategy;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qsheker.weatherapplication.domain.db.Weather;
import org.qsheker.weatherapplication.domain.db.WeatherRepository;
import org.qsheker.weatherapplication.strategy.WeatherStrategy;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
@Component
public class LazyFetchStrategy implements WeatherStrategy {
    private final Map<String, Weather> cache = new HashMap<>();
    private final WeatherRepository weatherRepository;
    private long lastUpdateTime = 0;
    private static final long CACHE_TTL = 300000;

    @Override
    public Optional<Weather> getWeatherData(String cityName) {
        var cacheKey = "weather:"+cityName;
        if(cache.containsKey(cacheKey)) {
            log.info("Weather with city-name={} found in cache", cityName);
            if(System.currentTimeMillis() - lastUpdateTime < CACHE_TTL){
                cache.remove(cacheKey);
            }
            return Optional.of(cache.get(cacheKey));
        }
        log.info("Weather with city-name={} not found in cache", cityName);
        Weather freshData = weatherRepository.getWeathersByCity(cityName).orElseThrow(
                ()->new EntityNotFoundException("Weather with city:"+cityName+" not found!")
        );

        cache.put(cacheKey, freshData);
        return Optional.of(freshData);
    }
}
