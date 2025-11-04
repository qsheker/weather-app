package org.qsheker.weatherapplication.domain.service;

import org.qsheker.weatherapplication.domain.ObserverType;
import org.qsheker.weatherapplication.domain.db.User;

import java.util.List;

public interface UserService {
    List<User> findByObserveType(ObserverType observerType);
    void save(User user);
}
