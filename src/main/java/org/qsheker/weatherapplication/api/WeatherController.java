package org.qsheker.weatherapplication.api;

import org.qsheker.weatherapplication.domain.Strategy;
import org.qsheker.weatherapplication.domain.db.Weather;
import org.qsheker.weatherapplication.strategy.WeatherStrategy;
import org.qsheker.weatherapplication.strategy.concreateStrategy.ApiStrategy;
import org.qsheker.weatherapplication.strategy.concreateStrategy.FreshFetchStrategy;
import org.qsheker.weatherapplication.strategy.concreateStrategy.LazyFetchStrategy;
import org.qsheker.weatherapplication.web.dto.WeatherResponseDto;
import org.qsheker.weatherapplication.web.mapper.WeatherResponseMapper;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("api/v1/weathers")
public class WeatherController {

    private final ApiStrategy apiStrategy;
    private final FreshFetchStrategy freshFetchStrategy;
    private final LazyFetchStrategy lazyFetchStrategy;

    public WeatherController(RestTemplate restTemplate, ApiStrategy apiStrategy, FreshFetchStrategy freshFetchStrategy, LazyFetchStrategy lazyFetchStrategy) {
        this.apiStrategy = apiStrategy;
        this.freshFetchStrategy = freshFetchStrategy;
        this.lazyFetchStrategy = lazyFetchStrategy;
    }

    @GetMapping("/{city-name}")
    public WeatherResponseDto getWeatherByCityName(@PathVariable("city-name") String city,
                                                   @RequestParam(value = "fetch-strategy", defaultValue = "DEFAULT") Strategy strategy){
        var concreteStrategy = resolve(strategy);
        Weather weather = concreteStrategy.getWeatherData(city)
                .orElseThrow(() -> new RuntimeException("Weather data not found for city: " + city));
        return WeatherResponseMapper.toWeatherApiFormat(weather);
    }

    private WeatherStrategy resolve(Strategy strategy){
        return switch (strategy){
            case API -> apiStrategy;
            case DEFAULT -> freshFetchStrategy;
            case CACHE_FRIENDLY -> lazyFetchStrategy;
        };
    }
}
