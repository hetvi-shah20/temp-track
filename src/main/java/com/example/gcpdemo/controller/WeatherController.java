package com.example.gcpdemo.controller;

import com.example.gcpdemo.entity.WeatherLog;
import com.example.gcpdemo.repository.WeatherRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;
import java.util.Objects;

@RestController
@RequestMapping("/api/weather")
public class WeatherController {

    @Autowired
    private WeatherRepository weatherRepository;

    private static final String BASE_URL = "https://wttr.in/";
    private static final String TEMPERATURE_FORMAT = "?format=%t+%f";
    private static final String WEATHER_CONDITION_FORMAT = "?format=%C";


    @GetMapping("/")
    public String getWeather(@RequestParam("city") String city) {
        RestTemplate restTemplate = new RestTemplate();
        String WEATHER_API_URL = BASE_URL + city + TEMPERATURE_FORMAT;
        String WEATHER_CONDITION_API_URL = BASE_URL + city + WEATHER_CONDITION_FORMAT;
        try{
            ResponseEntity<String> temperature = restTemplate.getForEntity(WEATHER_API_URL, String.class);
            ResponseEntity<String> condition = restTemplate.getForEntity(WEATHER_CONDITION_API_URL, String.class);

            if(temperature.getBody()!= null && condition.getBody() != null) {
                List<String> list = Arrays.stream(temperature.getBody().split(" ")).map(String::trim).toList();
                WeatherLog weatherLog = new WeatherLog();
                weatherLog.setCity(city);
                weatherLog.setCondition(condition.getBody());
                weatherLog.setTemperature(list.getFirst());
                weatherLog.setTemperatureFeelsLike(list.getLast());
                weatherRepository.save(weatherLog);
                System.out.println("Weather data saved successfully.");
            }
            return "Today's weather in " +city +": "+ temperature.getBody() + " and weather condition is like  " + condition.getBody();

        }catch (Exception e){
            return "Could not fetch weather data.Please check the city name.";
        }
    }


}
