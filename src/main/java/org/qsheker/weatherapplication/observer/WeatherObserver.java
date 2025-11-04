package org.qsheker.weatherapplication.observer;

import org.qsheker.weatherapplication.domain.db.Weather;

public interface WeatherObserver {
    String update(Weather weather);
}
