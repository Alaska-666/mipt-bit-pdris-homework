package ru.mipt.bit.homework.weathercurrencyapp.entities;

import org.junit.jupiter.api.Test;
import ru.mipt.bit.homework.weathercurrencyapp.services.weather.Weather;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class WeatherEntityTest {

    @Test
    void getWeather() {
        Weather weather = new Weather(-6.1, -14.1, -6.8, 9.2, 1.4, 5.3);
        String date = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
        WeatherEntityId id = new WeatherEntityId(date, "Paris");
        WeatherEntity weatherEntity = new WeatherEntity(id, weather);
        assertEquals("Weather{maxTempC=-6.1, minTempC=-14.1, avgTempC=-6.8, maxWindMph=9.2, totalPrecipMm=1.4, avgVisKm=5.3}", weatherEntity.getWeather().toString());
        assertEquals(Weather.class, weatherEntity.getWeather().getClass());

    }

    @Test
    void getId() {
        String city = "Paris";
        Weather weather = new Weather(-6.1, -14.1, -6.8, 9.2, 1.4, 5.3);
        String date = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
        WeatherEntityId id = new WeatherEntityId(date, city);
        WeatherEntity weatherEntity = new WeatherEntity(id, weather);
        assertEquals(WeatherEntityId.class, weatherEntity.getId().getClass());
        assertEquals(date, weatherEntity.getId().getDate());
        assertEquals(city, weatherEntity.getId().getCity());
    }
}