package com.codehive.apiclima.dto;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class OpenMeteoResponse {

    private double latitude;
    private double longitude;

    @JsonProperty("generationtime_ms")
    private double generationtimeMs;

    private int utcOffsetSeconds;

    @JsonProperty("timezone")
    private String timeZone;

    @JsonProperty("timezone_abbreviation")
    private String timezoneAbbreviation;

    private double elevation;

    @JsonProperty("hourly_units")
    private HourlyUnits hourlyUnits;

    @Getter
    @Setter
    public static class HourlyUnits {
        private String time;

        @JsonProperty("temperature_2m")
        private String temperature2m;

        @JsonProperty("weather_code")
        private String weatherCode;
    }
    private Hourly hourly;
    @Getter
    @Setter
    public static class Hourly {
        private List<String> time;

        @JsonProperty("temperature_2m")
        private List<Double> temperature2m;

        @JsonProperty("weather_code")
        private List<Integer> weatherCode;
    }

    @JsonProperty("daily_units")
    private DailyUnits dailyUnits;

    @Getter
    @Setter
    public static class DailyUnits {
        private String time;

        @JsonProperty("weather_code")
        private String weatherCode;

        private String sunrise;
        private String sunset;
    }

    private Daily daily;
    @Getter
    @Setter
    public static class Daily {
        private List<String> time;

        @JsonProperty("weather_code")
        private List<Integer> weatherCode;

        private List<String> sunrise;
        private List<String> sunset;
    }


}