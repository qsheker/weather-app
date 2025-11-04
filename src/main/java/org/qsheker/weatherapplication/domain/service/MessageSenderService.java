package org.qsheker.weatherapplication.domain.service;

import org.qsheker.weatherapplication.domain.ObserverType;

import java.util.List;

public interface MessageSenderService {
    void send(ObserverType observerType);
}
