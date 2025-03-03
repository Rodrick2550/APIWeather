package com.codehive.apiclima.dtos.daily;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DailyUnitsResponse {

    private String time;

    @JsonProperty("weather_code")
    private String weatherCode;

    private String sunrise;
    private String sunset;
}
