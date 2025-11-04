package org.qsheker.weatherapplication.domain.service.impl;

import org.qsheker.weatherapplication.domain.ObserverType;
import org.qsheker.weatherapplication.domain.db.User;
import org.qsheker.weatherapplication.domain.db.UserRepository;
import org.qsheker.weatherapplication.domain.service.UserService;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;

    public UserServiceImpl(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public List<User> findByObserveType(ObserverType observerType) {
        return userRepository.findByObserverType(observerType);
    }

    @Override
    public void save(User user) {
        userRepository.save(user);
    }
}
