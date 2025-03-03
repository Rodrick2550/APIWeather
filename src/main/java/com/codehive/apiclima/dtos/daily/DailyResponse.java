package com.codehive.apiclima.dtos.daily;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class DailyResponse {

    private List<String> time;

    @JsonProperty("weather_code")
    private List<Integer> weatherCode;

    private List<String> sunrise;
    private List<String> sunset;
}
