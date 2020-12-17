package ru.mipt.bit.homework.weathercurrencyapp.entities;

import org.junit.jupiter.api.Test;
import ru.mipt.bit.homework.weathercurrencyapp.services.weather.Weather;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class WeatherEntityIdTest {

    @Test
    void getDate() {
        String city = "Paris";
        String date = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now().minusDays(1));
        WeatherEntityId id = new WeatherEntityId(date, city);
        assertEquals(date, id.getDate());
    }

    @Test
    void getCity() {
        String city = "Moscow";
        String date = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now().minusDays(7));
        WeatherEntityId id = new WeatherEntityId(date, city);
        assertEquals(city, id.getCity());
    }

    @Test
    void setDate() {
        WeatherEntityId id = new WeatherEntityId();
        String date = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
        assertNull(id.getDate());
        id.setDate(date);
        assertEquals(date, id.getDate());
    }

    @Test
    void setCity() {
        WeatherEntityId id = new WeatherEntityId();
        assertNull(id.getCity());
        String city = "Magix";
        id.setCity(city);
        assertEquals(city, id.getCity());
    }

    @Test
    void testEquals() {
        String date = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
        String city = "Moscow";
        WeatherEntityId id = new WeatherEntityId(date, city);
        assertEquals(id, id);
        assertNotEquals(id, new WeatherEntity(id, new Weather()));
        WeatherEntityId otherId = new WeatherEntityId(date, "Paris");
        assertNotEquals(id, otherId);
        otherId = new WeatherEntityId(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now().minusDays(7)), city);
        assertNotEquals(id, otherId);
        otherId = new WeatherEntityId(date, city);
        assertEquals(id, otherId);
    }

    @Test
    void testHashCode() {
        String date = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now());
        String city = "Moscow";
        WeatherEntityId id = new WeatherEntityId(date, city);
        WeatherEntityId otherId = new WeatherEntityId(date, "Paris");
        assertNotEquals(id.hashCode(), otherId.hashCode());
        otherId = new WeatherEntityId(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now().minusDays(7)), city);
        assertNotEquals(id.hashCode(), otherId.hashCode());
        otherId = new WeatherEntityId(date, city);
        assertEquals(id.hashCode(), otherId.hashCode());
    }
}