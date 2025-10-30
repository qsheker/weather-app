package org.qsheker.weatherapplication.strategy.concreateStrategy;

import jakarta.persistence.EntityNotFoundException;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qsheker.weatherapplication.domain.db.Weather;
import org.qsheker.weatherapplication.domain.db.WeatherRepository;
import org.qsheker.weatherapplication.strategy.WeatherStrategy;

import java.util.HashMap;
import java.util.Map;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class LazyFetchStrategy implements WeatherStrategy {
    private final Map<String, Weather> cache = new HashMap<>();
    private final WeatherRepository weatherRepository;
    private long lastUpdateTime = 0;
    private static final long CACHE_TTL = 300000;

    @Override
    public Optional<Weather> getWeatherData(Long id) {
        var cacheKey = "weather:"+id;
        if(cache.containsKey(cacheKey)) {
            log.info("Weather with id={} found in cache", id);
            if(System.currentTimeMillis() - lastUpdateTime < CACHE_TTL){
                cache.remove(cacheKey);
            }
            return Optional.of(cache.get(cacheKey));
        }
        log.info("Weather with id={} not found in cache", id);
        Weather freshData = weatherRepository.getWeathersById(id).orElseThrow(
                ()->new EntityNotFoundException("Weather with id:"+id+" not found!")
        );

        cache.put(cacheKey, freshData);
        return Optional.of(freshData);
    }
}
