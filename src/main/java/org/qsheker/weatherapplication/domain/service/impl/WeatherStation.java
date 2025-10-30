package org.qsheker.weatherapplication.domain.service;

import org.qsheker.weatherapplication.domain.db.Weather;
import org.qsheker.weatherapplication.domain.db.WeatherRepository;
import org.qsheker.weatherapplication.domain.service.impl.WeatherService;
import org.qsheker.weatherapplication.strategy.WeatherStrategyDB;
import org.springframework.stereotype.Service;


@Service
public class WeatherStation implements WeatherService {
    private final WeatherStrategyDB strategy;
    private final WeatherRepository weatherRepository;

    public WeatherStation(WeatherStrategyDB strategy, WeatherRepository weatherRepository){
        this.strategy = strategy;
        this.weatherRepository = weatherRepository;
    }

    @Override
    public Weather getWeatherById(Long id) {
        return strategy.getWeatherData(id).orElseThrow();
    }
}
