package org.qsheker.weatherapplication.strategy.concreateStrategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qsheker.weatherapplication.domain.db.Weather;
import org.qsheker.weatherapplication.domain.db.WeatherRepository;
import org.qsheker.weatherapplication.strategy.WeatherStrategy;
import org.springframework.stereotype.Component;

import java.util.Optional;

@Slf4j
@Component
@RequiredArgsConstructor
public class FreshFetchStrategy implements WeatherStrategy {
    private final WeatherRepository weatherRepository;

    @Override
    public Optional<Weather> getWeatherData(String cityName) {
        log.info("Get weather data with city-name={} from db",cityName);
        return weatherRepository.findByCity(cityName);
    }
}
