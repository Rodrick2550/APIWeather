package com.codehive.apiclima.service;

import com.codehive.apiclima.dto.OpenMeteoProcessResponse;
import com.codehive.apiclima.dto.OpenMeteoResponse;
import com.codehive.apiclima.utils.CodeWeatherMap;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenMeteoService {

    private final WebClient webClient;
    private final CodeWeatherMap codeWeatherMap;


    public OpenMeteoService(
            @Value("${open_meteo.api.url}") String baseUrl, CodeWeatherMap codeWeatherMap) {
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
        this.codeWeatherMap = codeWeatherMap;
    }

    public OpenMeteoResponse getOriginalResponse(
            double latitude, double longitude, String startDate, String endDate) {
        return webClient.get()
                .uri(uriBuilder -> uriBuilder
                        .queryParam("latitude", latitude)
                        .queryParam("longitude", longitude)
                        .queryParam("start_date", startDate)
                        .queryParam("end_date", endDate)
                        .queryParam("hourly", "temperature_2m,weather_code")
                        .queryParam("daily","weather_code,sunrise,sunset")
                        .build())
                .retrieve()
                .bodyToMono(OpenMeteoResponse.class)
                .block();
    }

    public OpenMeteoProcessResponse getProcessResponse
            (double latitude, double longitude, String startDate, String endDate){

        OpenMeteoResponse openMeteoResponse = getOriginalResponse(latitude, longitude, startDate, endDate);
        return processResponse(openMeteoResponse);
    }

    private OpenMeteoProcessResponse processResponse (OpenMeteoResponse openMeteoResponse){
        OpenMeteoProcessResponse processResponse = new OpenMeteoProcessResponse();
        OpenMeteoProcessResponse.Daily daily = new OpenMeteoProcessResponse.Daily();

       daily.setTime(openMeteoResponse.getDaily().getTime().get(0));
       daily.setWeatherCode(codeWeatherMap.getWeatherDescription(openMeteoResponse.getDaily().getWeatherCode().get(0)));
       daily.setSunrise(openMeteoResponse.getDaily().getSunrise().get(0));
       daily.setSunset(openMeteoResponse.getDaily().getSunset().get(0));


        List<Map<String, String>> hourly = new ArrayList<>();
        for (int i = 0; i < openMeteoResponse.getHourly().getTime().size(); i++) {
            String time = openMeteoResponse.getHourly().getTime().get(i);
            int weatherCode = openMeteoResponse.getHourly().getWeatherCode().get(i);

            Map<String, String> hourData = new HashMap<>();
            hourData.put(time, codeWeatherMap.getWeatherDescription(weatherCode));
            hourly.add(hourData);
        }

        daily.setHourly(hourly);
        processResponse.setDaily(daily);

        return processResponse;

    }


}
