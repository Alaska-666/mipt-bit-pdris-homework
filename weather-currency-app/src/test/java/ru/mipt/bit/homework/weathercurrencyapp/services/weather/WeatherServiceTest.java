package ru.mipt.bit.homework.weathercurrencyapp.services.weather;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.web.client.HttpClientErrorException;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class WeatherServiceTest {
    @Autowired
    private WeatherService weatherService;

    @Test
    void getWeather() {
        List<Weather> weathers = weatherService.getWeather(2, "Moscow");
        assertEquals(2, weathers.size());
        for (Weather weather : weathers) {
            assertEquals(String.format("Weather{maxTempC=%.1f, minTempC=%.1f, avgTempC=%.1f, maxWindMph=%.1f, " +
                            "totalPrecipMm=%.1f, avgVisKm=%.1f}", weather.getMaxTempC(), weather.getMinTempC(),
                    weather.getAvgTempC(), weather.getMaxWindMph(), weather.getTotalPrecipMm(), weather.getAvgVisKm()),
                    weather.toString());
            assertTrue(weather.getMinTempC() <= weather.getAvgTempC() &&
                    weather.getAvgTempC() <= weather.getMaxTempC());
        }
    }

    @Test
    void getWeatherNullDays() {
        List<Weather> weathers = weatherService.getWeather(0, "Moscow");
        assertTrue(weathers.isEmpty());
    }

    @Test
    void getWeatherMinusDays() {
        List<Weather> weathers = weatherService.getWeather(-9, "Moscow");
        assertTrue(weathers.isEmpty());
    }

    @Test
    void getWeatherIncorrectCity() {
        Exception exception = assertThrows(HttpClientErrorException.class, () -> {
            weatherService.getWeather(2, "Magix");
        });
        assertEquals("400 Bad Request: [{\"error\":{\"code\":1006,\"message\":\"No matching location found.\"}}]",
                exception.getMessage());
    }
}