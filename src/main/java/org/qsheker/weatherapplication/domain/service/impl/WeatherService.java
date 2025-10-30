package org.qsheker.weatherapplication.domain.service.impl;

import org.qsheker.weatherapplication.domain.db.Weather;

public interface WeatherService {
    Weather getWeatherById(Long id);
    Weather createWeather(Weather weather);
    Weather updateWeather(Long id, Weather weather);
    void deleteWeather(Long id);
}
