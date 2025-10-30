package org.qsheker.weatherapplication.web.dto;

import lombok.Builder;
import lombok.Data;

@Data
@Builder
public class WeatherResponseDto {
    private Location location;
    private Current current;
    private String city;

    @Data
    public static class Location {
        private String name;
        private String country;
    }

    @Data
    public static class Current {
        private Double temp_c;
        private Integer humidity;
        private Double pressure_mb;
        private Double wind_kph;
        private Condition condition;

        @Data
        public static class Condition {
            private String text;
        }
    }
}