package org.qsheker.weatherapplication.domain.db;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Optional<Weather> getWeathersById(Long id);
}
