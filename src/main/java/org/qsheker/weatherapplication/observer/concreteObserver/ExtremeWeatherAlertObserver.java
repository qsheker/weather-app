package org.qsheker.weatherapplication.observer.concreteObserver;

import lombok.extern.slf4j.Slf4j;
import org.qsheker.weatherapplication.domain.WeatherCondition;
import org.qsheker.weatherapplication.domain.db.Weather;
import org.qsheker.weatherapplication.observer.WeatherObserver;
import org.springframework.stereotype.Component;

@Component
@Slf4j
public class ExtremeWeatherAlertObserver implements WeatherObserver {
    @Override
    public void update(Weather weather) {
        if (weather.getTemperature() > 40) {
            log.warn("Extreme heat in {}: {}°C", weather.getCity(), weather.getTemperature());
        }
        if (weather.getTemperature() < -20) {
            log.warn("Extreme cold in {}: {}°C", weather.getCity(), weather.getTemperature());
        }
        if (weather.getWeatherCondition() == WeatherCondition.THUNDERSTORM) {
            log.warn("Thunderstorm in {}", weather.getCity());
        }
    }
}
