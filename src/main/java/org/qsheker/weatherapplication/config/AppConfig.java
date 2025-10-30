package org.qsheker.weatherapplication.config;

import org.qsheker.weatherapplication.domain.db.WeatherRepository;
import org.qsheker.weatherapplication.strategy.concreateStrategy.ApiStrategy;
import org.qsheker.weatherapplication.strategy.concreateStrategy.FreshFetchStrategy;
import org.qsheker.weatherapplication.strategy.concreateStrategy.LazyFetchStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class AppConfig {
    @Bean
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }

    @Bean
    public ApiStrategy apiStrategy(RestTemplate restTemplate){
        return new ApiStrategy(restTemplate);
    }
    @Bean
    public FreshFetchStrategy freshFetchStrategy(WeatherRepository weatherRepository){
        return new FreshFetchStrategy(weatherRepository);
    }
    @Bean
    public LazyFetchStrategy lazyFetchStrategy(WeatherRepository weatherRepository){
        return new LazyFetchStrategy(weatherRepository);
    }
}
