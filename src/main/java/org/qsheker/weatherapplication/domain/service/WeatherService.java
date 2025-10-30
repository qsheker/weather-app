package org.qsheker.weatherapplication.domain.service;

import org.qsheker.weatherapplication.domain.db.Weather;

public interface WeatherService {
    Weather getWeatherByCityName(String cityName);
    Weather saveWeather(Weather weather);
    void deleteWeather(Long id);
}
