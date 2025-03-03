package com.codehive.apiclima.dtos.hourly;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class HourlyUnitsResponse {

    private String time;

    @JsonProperty("temperature_2m")
    private String temperature2m;

    @JsonProperty("weather_code")
    private String weatherCode;
}
