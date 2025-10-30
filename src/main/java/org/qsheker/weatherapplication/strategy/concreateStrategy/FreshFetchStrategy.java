package org.qsheker.weatherapplication.strategy.concreateStrategy;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.qsheker.weatherapplication.domain.db.Weather;
import org.qsheker.weatherapplication.domain.db.WeatherRepository;
import org.qsheker.weatherapplication.strategy.WeatherStrategy;
import java.util.Optional;

@Slf4j
@RequiredArgsConstructor
public class FreshFetchStrategy implements WeatherStrategy {
    private final WeatherRepository weatherRepository;

    @Override
    public Optional<Weather> getWeatherData(Long id) {
        log.info("Get weather data with id={} from db",id);
        return weatherRepository.findById(id);
    }
}
