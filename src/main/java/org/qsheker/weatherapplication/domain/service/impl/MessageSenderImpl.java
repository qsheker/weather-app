package org.qsheker.weatherapplication.domain.service.impl;

import org.qsheker.weatherapplication.domain.ObserverType;
import org.qsheker.weatherapplication.domain.db.User;
import org.qsheker.weatherapplication.domain.db.UserRepository;
import org.qsheker.weatherapplication.domain.service.MessageSenderService;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MessageSenderImpl implements MessageSenderService {

    private final UserRepository userRepository;

    public MessageSenderImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public void send(ObserverType observerType) {
        List<User> users = userRepository.findByObserverType(observerType);
        List<String> phoneNumbers = users.stream()
                .map(user -> user.getPhoneNumber())
                .toList();
//        TODO sending message by using twiwlio library
    }
}
