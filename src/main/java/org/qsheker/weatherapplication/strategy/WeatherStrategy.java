package org.qsheker.weatherapplication.strategy;

import org.qsheker.weatherapplication.domain.db.Weather;

import java.util.Optional;

public interface WeatherStrategy {
    Optional<Weather> getWeatherData(Long id);
    Optional<Weather> getWeatherData(String cityName);
}
