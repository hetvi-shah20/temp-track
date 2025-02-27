package com.example.gcpdemo.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    private static final String WEATHER_API_URL = "https://wttr.in/Ottawa?format=%C+%t+%f";


    @GetMapping("/")
    public String getWeather() {
        RestTemplate restTemplate = new RestTemplate();
        ResponseEntity<String> response = restTemplate.getForEntity(WEATHER_API_URL, String.class);
        System.out.println("Today's weather in Ottawa: " + response.getBody());
        return response.getBody() != null ? "Today's weather in Ottawa: " + response.getBody() : "Could not fetch weather data.";
    }
}
