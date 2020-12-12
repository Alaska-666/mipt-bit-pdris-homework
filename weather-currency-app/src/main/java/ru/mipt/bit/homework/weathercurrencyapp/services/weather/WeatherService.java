package ru.mipt.bit.homework.weathercurrencyapp.services.weather;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mipt.bit.homework.weathercurrencyapp.entities.WeatherEntity;
import ru.mipt.bit.homework.weathercurrencyapp.entities.WeatherEntityId;
import ru.mipt.bit.homework.weathercurrencyapp.repositories.WeatherRepository;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class WeatherService {
    @Value("${api.weather.key}")
    private String apiKey;

    @Autowired
    private WeatherRepository weatherRepository;

    private Weather getWeatherDaysBefore(int daysBefore, String city) {
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDate.now().minusDays(daysBefore));
        Optional<WeatherEntity> weatherEntity = weatherRepository.findById(new WeatherEntityId(date, city));
        return weatherEntity.map(WeatherEntity::getWeather).orElseGet(() -> getWeatherProperties(date, city));
    }

    private Weather getWeatherProperties(String date, String city) {
        String url = "http://api.weatherapi.com/v1/history.json?key=" + apiKey + "&q=" + city + "&dt=" + date;
        JsonWeatherResponse response = new RestTemplate().getForObject(url, JsonWeatherResponse.class);
        if (response == null) {
            return new Weather();
        }
        return response.forecast.forecastDay.get(0).weather;
    }

    public List<Weather> getWeather(int days, String city) {
        return IntStream.range(0, days)
                .mapToObj(i -> getWeatherDaysBefore(i, city))
                .collect(Collectors.toList());
    }
}
