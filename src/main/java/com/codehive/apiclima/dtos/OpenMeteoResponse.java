package com.codehive.apiclima.dtos;

import com.codehive.apiclima.dtos.daily.DailyResponse;
import com.codehive.apiclima.dtos.daily.DailyUnitsResponse;
import com.codehive.apiclima.dtos.hourly.HourlyResponse;
import com.codehive.apiclima.dtos.hourly.HourlyUnitsResponse;
import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;


@Getter
@Setter
public class OpenMeteoResponse {

    private double latitude;
    private double longitude;

    @JsonProperty("generationtime_ms")
    private double generationTimeMs;

    private int utcOffsetSeconds;

    @JsonProperty("timezone")
    private String timeZone;

    @JsonProperty("timezone_abbreviation")
    private String timezoneAbbreviation;

    private double elevation;

    @JsonProperty("hourly_units")
    private HourlyUnitsResponse hourlyUnits;

    private HourlyResponse hourly;

    @JsonProperty("daily_units")
    private DailyUnitsResponse dailyUnits;

    private DailyResponse daily;
}