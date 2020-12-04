package ru.mipt.bit.homework.weathercurrencyapp.services.weather;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

@JsonIgnoreProperties(ignoreUnknown = true)
public class JsonWeatherResponse {
    @JsonProperty("forecast")
    public ListWeatherInfo forecast;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class ListWeatherInfo {
    @JsonProperty("forecastday")
    public List<WeatherInfo> forecastDay;
}

@JsonIgnoreProperties(ignoreUnknown = true)
class WeatherInfo {
    @JsonProperty("day")
    public Weather weather;
}
