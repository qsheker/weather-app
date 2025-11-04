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
    public String update(Weather weather) {
        if (weather.getTemperature() > 40) {
            return String.format("Extreme heat in %s: %.1f°C", weather.getCity(), weather.getTemperature());
        }
        if (weather.getTemperature() < -10) {
            return String.format("Extreme cold in %s: %.1f°C", weather.getCity(), weather.getTemperature());
        }
        if (weather.getWeatherCondition() == WeatherCondition.THUNDERSTORM) {
            return String.format("Thunderstorm in %s", weather.getCity());
        }
        return "";
    }
}
