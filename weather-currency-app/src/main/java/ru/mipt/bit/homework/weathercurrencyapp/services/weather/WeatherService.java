package ru.mipt.bit.homework.weathercurrencyapp.services.weather;

import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class WeatherService {
    @Value("${api.weather.key}")
    private String apiKey;

    private Weather getResponseFromUrl(String url) {
        JsonWeatherResponse response = new RestTemplate().getForObject(url, JsonWeatherResponse.class);
        assert response != null;
        return response.forecast.forecastDay.get(0).weather;
    }

    private Weather getWeatherDaysBefore(int daysBefore, String city) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        String url = "http://api.weatherapi.com/v1/history.json?key=" + apiKey + "&q=" + city + "&dt=" + dtf.format(now.minusDays(daysBefore));
        return getResponseFromUrl(url);
    }

    public List<Weather> getWeather(int days, String city) {
        return IntStream.range(0, days)
                .mapToObj(i -> getWeatherDaysBefore(i, city))
                .collect(Collectors.toList());
    }
}
