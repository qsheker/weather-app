package org.qsheker.weatherapplication.domain.db;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface WeatherRepository extends JpaRepository<Weather, Long> {
    Optional<Weather> getWeathersById(Long id);

    Optional<Weather> findByCity(String city);

    Optional<Weather> getWeathersByCity(String city);
}
