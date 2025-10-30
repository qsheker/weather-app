package org.qsheker.weatherapplication.domain.service;

import lombok.RequiredArgsConstructor;
import org.qsheker.weatherapplication.domain.db.Weather;
import org.qsheker.weatherapplication.domain.db.WeatherRepository;
import org.qsheker.weatherapplication.domain.service.impl.WeatherService;
import org.qsheker.weatherapplication.strategy.WeatherStrategy;
import org.springframework.stereotype.Service;


@Service
public class WeatherStation implements WeatherService {
    private final WeatherStrategy strategy;
    private final WeatherRepository weatherRepository;

    public WeatherStation(WeatherStrategy strategy, WeatherRepository weatherRepository){
        this.strategy = strategy;
        this.weatherRepository = weatherRepository;
    }

    @Override
    public Weather getWeatherById(Long id) {
        return strategy.getWeatherData(id).orElseThrow();
    }

    @Override
    public Weather createWeather(Weather weather) {
        return weatherRepository.save(weather);
    }

    @Override
    public Weather updateWeather(Long id, Weather weather) {
        if(!weatherRepository.existsById(id)){
            throw new RuntimeException();
        }
        return weatherRepository.save(weather);
    }

    @Override
    public void deleteWeather(Long id) {
        if(!weatherRepository.existsById(id)){
            throw new RuntimeException();
        }
        var weather = weatherRepository.getWeathersById(id).orElseThrow();
        weatherRepository.delete(weather);
    }
}
