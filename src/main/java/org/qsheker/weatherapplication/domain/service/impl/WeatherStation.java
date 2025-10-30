package org.qsheker.weatherapplication.domain.service.impl;

import org.qsheker.weatherapplication.domain.service.WeatherService;
import org.qsheker.weatherapplication.domain.db.Weather;
import org.qsheker.weatherapplication.domain.db.WeatherRepository;
import org.qsheker.weatherapplication.strategy.WeatherStrategy;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Service;


@Service
public class WeatherStation implements WeatherService {
    private final WeatherStrategy strategy;
    private final WeatherRepository weatherRepository;

    public WeatherStation(@Qualifier("apiStrategy") WeatherStrategy strategy, WeatherRepository weatherRepository){
        this.strategy = strategy;
        this.weatherRepository = weatherRepository;
    }

    @Override
    public Weather getWeatherByCityName(String cityName) {
        return strategy.getWeatherData(cityName).orElseThrow();
    }

    @Override
    public Weather saveWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    @Override
    public void deleteWeather(Long id) {
        Weather weather = weatherRepository.getWeathersById(id).orElseThrow();
        weatherRepository.delete(weather);
    }
}
