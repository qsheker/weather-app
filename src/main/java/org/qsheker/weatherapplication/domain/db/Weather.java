package org.qsheker.weatherapplication.domain.db;

import jakarta.persistence.*;
import lombok.Data;
import org.qsheker.weatherapplication.domain.DataSource;
import org.qsheker.weatherapplication.domain.WeatherCondition;
import java.time.LocalDateTime;

@Data
@Entity
@Table(name = "weather_data")
public class Weather {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String city;
    private String country;
    private Double temperature;
    private Integer humidity;
    private Double pressure;
    private Double windSpeed;
    private String description;

    @Enumerated(EnumType.STRING)
    private WeatherCondition weatherCondition;

    @Enumerated(EnumType.STRING)
    private DataSource dataSource;

    private Boolean isCurrent = false;

    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @PrePersist
    protected void onCreate() {
        createdAt = LocalDateTime.now();
    }

    @PreUpdate
    protected void onUpdate() {
        updatedAt = LocalDateTime.now();
    }

    public static WeatherBuilder builder() {
        return new WeatherBuilder();
    }

    public static class WeatherBuilder {
        private String city;
        private Double temperature;
        private Integer humidity;
        private Double pressure;
        private Double windSpeed;
        private String description;
        private WeatherCondition weatherCondition;
        private DataSource dataSource;
        private Boolean isCurrent;

        public WeatherBuilder city(String city) {
            this.city = city;
            return this;
        }

        public WeatherBuilder temperature(Double temperature) {
            this.temperature = temperature;
            return this;
        }

        public WeatherBuilder humidity(Integer humidity) {
            this.humidity = humidity;
            return this;
        }

        public WeatherBuilder pressure(Double pressure) {
            this.pressure = pressure;
            return this;
        }

        public WeatherBuilder windSpeed(Double windSpeed) {
            this.windSpeed = windSpeed;
            return this;
        }

        public WeatherBuilder description(String description) {
            this.description = description;
            return this;
        }

        public WeatherBuilder weatherCondition(WeatherCondition weatherCondition) {
            this.weatherCondition = weatherCondition;
            return this;
        }

        public WeatherBuilder dataSource(DataSource dataSource) {
            this.dataSource = dataSource;
            return this;
        }

        public WeatherBuilder isCurrent(Boolean isCurrent) {
            this.isCurrent = isCurrent;
            return this;
        }

        public Weather build() {
            Weather weather = new Weather();
            weather.setCity(this.city);
            weather.setTemperature(this.temperature);
            weather.setHumidity(this.humidity);
            weather.setPressure(this.pressure);
            weather.setWindSpeed(this.windSpeed);
            weather.setDescription(this.description);
            weather.setWeatherCondition(this.weatherCondition);
            weather.setDataSource(this.dataSource);
            weather.setIsCurrent(this.isCurrent != null ? this.isCurrent : false);
            return weather;
        }
    }
}
