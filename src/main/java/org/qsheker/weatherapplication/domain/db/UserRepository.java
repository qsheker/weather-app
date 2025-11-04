package org.qsheker.weatherapplication.domain.db;

import org.qsheker.weatherapplication.domain.ObserverType;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Long> {
    List<User> findByObserverType(ObserverType observerType);
}
