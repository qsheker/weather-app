package org.qsheker.weatherapplication.domain.service.impl;

import jakarta.persistence.EntityNotFoundException;
import lombok.extern.slf4j.Slf4j;
import org.qsheker.weatherapplication.domain.ObserverType;
import org.qsheker.weatherapplication.domain.Strategy;
import org.qsheker.weatherapplication.domain.db.*;
import org.qsheker.weatherapplication.domain.service.MessageSenderService;
import org.qsheker.weatherapplication.observer.WeatherObserver;
import org.qsheker.weatherapplication.observer.concreteObserver.ExtremeWeatherAlertObserver;
import org.qsheker.weatherapplication.strategy.WeatherStrategy;
import org.qsheker.weatherapplication.strategy.concreateStrategy.ApiStrategy;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
public class MessageSenderImpl implements MessageSenderService {

    private final UserRepository userRepository;
    private final MessageRepository messageRepository;
    private final ExtremeWeatherAlertObserver extremeWeatherAlertObserver;
    private final ApiStrategy apiStrategy;

    public MessageSenderImpl(UserRepository userRepository, MessageRepository messageRepository, ExtremeWeatherAlertObserver extremeWeatherAlertObserver, ApiStrategy apiStrategy) {
        this.userRepository = userRepository;
        this.messageRepository = messageRepository;
        this.extremeWeatherAlertObserver = extremeWeatherAlertObserver;
        this.apiStrategy = apiStrategy;
    }

    @Override
    public void send(ObserverType observerType) {
        List<User> users = userRepository.findByObserverType(observerType);

        WeatherObserver concreteObserver = resolve(observerType);
        for (User user : users) {
            mainSender(user, concreteObserver);
        }
    }

    private void mainSender(User user,WeatherObserver weatherObserver){
        String cityName = user.getCityName();
        Weather weather = apiStrategy.getWeatherData(cityName).orElseThrow(
                ()-> new RuntimeException("fatal with fetching with api for city name: "+cityName)
        );

        String messageText = weatherObserver.update(weather);

        if(messageText.equals("")){
            return;
        }

        log.info("sending a message for user: "+ user.getId());
        Message message = new Message();
        message.setUser(user);
        message.setContent(messageText);
        messageRepository.save(message);
    }
    private WeatherObserver resolve(ObserverType observerType){
        return switch (observerType){
            case EXTREME_WEATHER_ALERT -> extremeWeatherAlertObserver;
        };
    }
}
