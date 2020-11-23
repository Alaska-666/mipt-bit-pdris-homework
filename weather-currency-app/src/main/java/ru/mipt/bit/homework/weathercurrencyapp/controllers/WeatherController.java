package ru.mipt.bit.homework.weathercurrencyapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mipt.bit.homework.weathercurrencyapp.services.weather.Weather;
import ru.mipt.bit.homework.weathercurrencyapp.services.weather.WeatherService;

import java.util.List;

@RestController
public class WeatherController {
    final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/weather")
    public List<Weather> getWeather(@RequestParam(required=false) Integer days, @RequestParam(required=false) String city) {
        if (days == null) {
            days = 1;
        }
        if (city == null) {
            city = "Moscow";
        }
        return weatherService.getWeather(days, city);
    }
}
