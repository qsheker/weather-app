package org.qsheker.weatherapplication.observer.concreteObserver;

import lombok.extern.slf4j.Slf4j;
import org.qsheker.weatherapplication.domain.db.Weather;
import org.qsheker.weatherapplication.observer.WeatherObserver;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;

@Component
@Slf4j
public class CityMonitorObserver implements WeatherObserver {
    private Set<String> monitoredCities = new HashSet<>();

    @Override
    public void update(Weather weather) {
        monitoredCities.add(weather.getCity());
        log.info("📍 Monitoring {} cities. Latest: {}", monitoredCities.size(), weather.getCity());
    }
}
