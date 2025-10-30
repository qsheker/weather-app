package org.qsheker.weatherapplication.observer;

import org.qsheker.weatherapplication.domain.db.Weather;

public interface WeatherObserver {
    void update(Weather weather);
}
