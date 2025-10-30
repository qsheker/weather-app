package org.qsheker.weatherapplication.web.mapper;

import org.qsheker.weatherapplication.domain.db.Weather;
import org.qsheker.weatherapplication.web.dto.WeatherResponseDto;

public class WeatherResponseMapper {
    public static WeatherResponseDto toWeatherApiFormat(Weather weather) {
        if (weather == null) return null;

        WeatherResponseDto.Location location = new WeatherResponseDto.Location();
        location.setName(weather.getCity());
        location.setCountry(weather.getCountry());

        WeatherResponseDto.Current.Condition condition = new WeatherResponseDto.Current.Condition();
        condition.setText(weather.getDescription());

        WeatherResponseDto.Current current = new WeatherResponseDto.Current();
        current.setTemp_c(weather.getTemperature());
        current.setHumidity(weather.getHumidity());
        current.setPressure_mb(weather.getPressure());
        current.setWind_kph(weather.getWindSpeed() * 3.6);
        current.setCondition(condition);

        return WeatherResponseDto.builder()
                .location(location)
                .current(current)
                .build();
    }
}
