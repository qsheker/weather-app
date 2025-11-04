package org.qsheker.weatherapplication.web.dto;

import lombok.Data;

@Data
public class PhoneRequestDto {
    private String phoneNumber;
    private String cityName;
}
