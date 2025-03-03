package com.codehive.apiclima.dtos;

import lombok.Getter;
import lombok.Setter;

import java.util.List;
import java.util.Map;

@Getter
@Setter
public class OpenMeteoProcessResponse {

    private Daily daily;

    @Getter
    @Setter
    public static class Daily{
        private String time;
        private String weatherCode;
        private String sunrise;
        private String sunset;
        private List<Map<String, String>> hourly;
    }

}
