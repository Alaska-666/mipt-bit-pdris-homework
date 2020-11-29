package ru.mipt.bit.homework.weathercurrencyapp.services.weather;

import javafx.util.Pair;
import org.dom4j.Document;
import org.dom4j.DocumentException;
import org.dom4j.DocumentHelper;
import org.dom4j.Node;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mipt.bit.homework.weathercurrencyapp.entities.WeatherEntity;
import ru.mipt.bit.homework.weathercurrencyapp.entities.WeatherEntityId;
import ru.mipt.bit.homework.weathercurrencyapp.repositories.WeatherRepository;

import java.lang.reflect.Field;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Arrays;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Service
public class WeatherService {
    @Value("${api.weather.key}")
    private String apiKey;
    private final List<String> properties = Arrays.asList("maxtemp_c", "mintemp_c", "avgtemp_c", "maxwind_mph",
            "totalprecip_mm", "avgvis_km");

    @Autowired
    private WeatherRepository weatherRepository;

    private String getResponseFromUrl(String url) {
        ResponseEntity<String> response = new RestTemplate().getForEntity(url, String.class);
        return response.getBody();
    }

    private Weather getWeatherDaysBefore(int daysBefore, String city) {
        String date = DateTimeFormatter.ofPattern("yyyy-MM-dd").format(LocalDateTime.now().minusDays(daysBefore));
        Optional<WeatherEntity> weatherEntity = weatherRepository.findById(new WeatherEntityId(date, city));
        if (weatherEntity.isPresent()) {
            return weatherEntity.get().getWeather();
        }
        return getWeatherProperties(date, city);
    }

    private Weather getWeatherProperties(String date, String city) {
        String url = "http://api.weatherapi.com/v1/history.xml?key=" + apiKey + "&q=" + city + "&dt=" + date;
        String body = getResponseFromUrl(url);

        Weather weather = new Weather();
        Document document;
        try {
            document = DocumentHelper.parseText(body);
        } catch (DocumentException ignored) {
            return weather;
        }

        for (String property : properties) {
            Node node = document.selectSingleNode("//" + property);
            try {
                Field field = Weather.class.getField(property);
                field.set(weather, new Double(node.getText()));
            } catch (Exception ignored) {}
        }
        weatherRepository.save(new WeatherEntity(date, city, weather));
        return weather;
    }

    public List<Weather> getWeather(int days, String city) {
        return IntStream.range(0, days)
                .map(i -> -i).sorted().map(i -> -i)
                .mapToObj(i -> getWeatherDaysBefore(i, city))
                .collect(Collectors.toList());
    }
}
