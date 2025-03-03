package com.codehive.apiclima.services;

import com.codehive.apiclima.dtos.BaseResponse;
import com.codehive.apiclima.dtos.OpenMeteoProcessResponse;
import com.codehive.apiclima.dtos.OpenMeteoResponse;
import com.codehive.apiclima.utils.CodeWeatherMap;
import jakarta.annotation.PostConstruct;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Service
public class OpenMeteoService {

    private WebClient webClient;
    private final CodeWeatherMap codeWeatherMap;
    @Value("${open_meteo.api.url}")
    private String apiUrl;

    @PostConstruct
    public void init() {
        String baseUrl = apiUrl;
        this.webClient = WebClient.builder()
                .baseUrl(baseUrl)
                .build();
    }

    public OpenMeteoService(CodeWeatherMap codeWeatherMap) {
        this.codeWeatherMap = codeWeatherMap;
    }

    public BaseResponse<OpenMeteoResponse> getOriginalResponse(
            double latitude, double longitude, String startDate, String endDate) {
        OpenMeteoResponse response = webClient.get()
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

        return BaseResponse.<OpenMeteoResponse>builder()
                .data(response)
                .message("Weather data retrieved successfully")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
    }

    public BaseResponse<OpenMeteoProcessResponse> getProcessResponse(
            double latitude, double longitude, String startDate, String endDate) {

        OpenMeteoResponse openMeteoResponse = getOriginalResponse(latitude, longitude, startDate, endDate).getData();
        OpenMeteoProcessResponse processResponse = processResponse(openMeteoResponse);

        return BaseResponse.<OpenMeteoProcessResponse>builder()
                .data(processResponse)
                .message("Processed weather data retrieved successfully")
                .success(Boolean.TRUE)
                .httpStatus(HttpStatus.OK)
                .build();
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
