package com.codehive.apiclima.dtos.hourly;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class HourlyResponse {

    private List<String> time;

    @JsonProperty("temperature_2m")
    private List<Double> temperature2m;

    @JsonProperty("weather_code")
    private List<Integer> weatherCode;

}
