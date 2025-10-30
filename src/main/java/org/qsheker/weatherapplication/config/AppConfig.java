package org.qsheker.weatherapplication.config;

import org.qsheker.weatherapplication.strategy.WeatherStrategy;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class appConfig{

    @Bean
    public WeatherStrategy weatherStrategy(WeatherStrategy weatherStrategy){
        return weatherStrategy;
    }
}
