package com.codehive.apiclima.controller;

import com.codehive.apiclima.dto.OpenMeteoProcessResponse;
import com.codehive.apiclima.dto.OpenMeteoResponse;
import com.codehive.apiclima.service.OpenMeteoService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/weather")
public class WeatherController {

    private final OpenMeteoService openMeteoService;

    public WeatherController(OpenMeteoService openMeteoService) {
        this.openMeteoService = openMeteoService;
    }

    @GetMapping
    public OpenMeteoResponse getOriginalWeatherData(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam("start_date") String startDate,
            @RequestParam("end_date") String endDate) {

        return openMeteoService.getOriginalResponse(latitude, longitude, startDate, endDate);
    }

    @GetMapping("/process")
    public OpenMeteoProcessResponse getProcessedWeatherData(
            @RequestParam double latitude,
            @RequestParam double longitude,
            @RequestParam("start_date") String startDate,
            @RequestParam("end_date") String endDate){

        return openMeteoService.getProcessResponse(latitude, longitude, startDate, endDate);
    }
}
