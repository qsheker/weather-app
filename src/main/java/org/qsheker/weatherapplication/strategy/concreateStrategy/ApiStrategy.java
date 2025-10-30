package org.qsheker.weatherapplication.strategy.concreateStrategy;

import lombok.RequiredArgsConstructor;
import org.qsheker.weatherapplication.domain.DataSource;
import org.qsheker.weatherapplication.domain.WeatherCondition;
import org.qsheker.weatherapplication.domain.db.Weather;
import org.qsheker.weatherapplication.strategy.WeatherStrategy;
import org.qsheker.weatherapplication.web.dto.WeatherResponseDto;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Optional;

@Component
@Primary
@RequiredArgsConstructor
public class ApiStrategy implements WeatherStrategy {
    @Value("${weather.api.api-key}")
    private String apiKey;

    @Value("${weather.api.url}")
    private String url;

    private final RestTemplate restTemplate;

    @Override
    public Optional<Weather> getWeatherData(String cityName) {
        try{
            String apiUrl = String.format("%s/current.json?key=%s&q=%s&aqi=no", url, apiKey, cityName);

            WeatherResponseDto weatherData = restTemplate.getForObject(apiUrl, WeatherResponseDto.class);
            if (weatherData != null && weatherData.getCurrent()!=null) {
                Weather weather = convertToWeather(weatherData, cityName);
                return Optional.of(weather);
            }

        } catch (Exception e) {
            System.err.println("Error fetching weather for " + cityName + ": " + e.getMessage());
        }

        return Optional.empty();

    }
    private Weather convertToWeather(WeatherResponseDto response, String requestedCity) {
        String cityName = response.getLocation().getName() != null ?
                response.getLocation().getName() : requestedCity;

        return Weather.builder()
                .city(cityName)
                .temperature(response.getCurrent().getTemp_c())
                .humidity(response.getCurrent().getHumidity())
                .pressure(response.getCurrent().getPressure_mb())
                .windSpeed(convertWindSpeed(response.getCurrent().getWind_kph()))
                .description(response.getCurrent().getCondition().getText())
                .weatherCondition(mapWeatherCondition(response.getCurrent().getCondition().getText()))
                .dataSource(DataSource.API)
                .isCurrent(true)
                .build();
    }
    private double convertWindSpeed(double windKph) {
        return Math.round((windKph / 3.6) * 100.0) / 100.0;
    }
    private WeatherCondition mapWeatherCondition(String conditionText) {
        if (conditionText == null) return WeatherCondition.UNKNOWN;

        String lowerCondition = conditionText.toLowerCase();

        if (lowerCondition.contains("sunny") || lowerCondition.contains("clear")) {
            return WeatherCondition.SUNNY;
        } else if (lowerCondition.contains("partly cloudy")) {
            return WeatherCondition.PARTLY_CLOUDY;
        } else if (lowerCondition.contains("cloud") || lowerCondition.contains("overcast")) {
            return WeatherCondition.CLOUDY;
        } else if (lowerCondition.contains("rain") || lowerCondition.contains("drizzle")) {
            return WeatherCondition.RAIN;
        } else if (lowerCondition.contains("storm") || lowerCondition.contains("thunder")) {
            return WeatherCondition.THUNDERSTORM;
        } else if (lowerCondition.contains("snow") || lowerCondition.contains("sleet")) {
            return WeatherCondition.SNOW;
        } else if (lowerCondition.contains("fog") || lowerCondition.contains("mist")) {
            return WeatherCondition.FOG;
        } else if (lowerCondition.contains("wind")) {
            return WeatherCondition.WINDY;
        } else {
            return WeatherCondition.UNKNOWN;
        }
    }
}
