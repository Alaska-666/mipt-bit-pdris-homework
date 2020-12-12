package ru.mipt.bit.homework.weathercurrencyapp.services.weather;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class WeatherTest {
    @Test
    void testConstructor() {
        Weather weather = new Weather();
        assertEquals(0.0, weather.getMaxTempC());
        assertEquals(0.0, weather.getMinTempC());
        assertEquals(0.0, weather.getAvgTempC());
        assertEquals(0.0, weather.getAvgVisKm());
        assertEquals(0.0, weather.getMaxWindMph());
        assertEquals(0.0, weather.getTotalPrecipMm());
    }

    @Test
    void testConstructor2() {
        Weather weather = new Weather(-6.1, -14.1, -6.8, 9.2, 1.4, 5.3);
        assertNotEquals(0.0, weather.getMaxTempC());
        assertNotEquals(0.0, weather.getMinTempC());
        assertNotEquals(0.0, weather.getAvgTempC());
        assertNotEquals(0.0, weather.getAvgVisKm());
        assertNotEquals(0.0, weather.getMaxWindMph());
        assertNotEquals(0.0, weather.getTotalPrecipMm());
    }

    @Test
    void testToString() {
        Weather weather = new Weather(-6.1, -14.1, -6.8, 9.2, 1.4, 5.3);
        assertEquals("Weather{maxTempC=-6.1, minTempC=-14.1, avgTempC=-6.8, maxWindMph=9.2, totalPrecipMm=1.4, avgVisKm=5.3}", weather.toString());
    }

    @Test
    void getMaxTempC() {
        Weather weather = new Weather(5, -14.1, -6.8, 9.2, 1.4, 5.3);
        assertEquals(5, weather.getMaxTempC());
    }

    @Test
    void getMinTempC() {
        Weather weather = new Weather(5, -10, -6.8, 9.2, 1.4, 5.3);
        assertEquals(-10, weather.getMinTempC());
    }

    @Test
    void getAvgTempC() {
        Weather weather = new Weather(5, -10, -6.8, 9.2, 1.4, 5.3);
        assertEquals(-6.8, weather.getAvgTempC());
    }

    @Test
    void getMaxWindMph() {
        Weather weather = new Weather(5, -10, -6.8, 9.2, 1.4, 5.3);
        assertEquals(9.2, weather.getMaxWindMph());
    }

    @Test
    void getTotalPrecipMm() {
        Weather weather = new Weather(5, -10, -6.8, 9.2, 1.4, 5.3);
        assertEquals(1.4, weather.getTotalPrecipMm());
    }

    @Test
    void getAvgVisKm() {
        Weather weather = new Weather(5, -10, -6.8, 9.2, 1.4, 5.3);
        assertEquals(5.3, weather.getAvgVisKm());
    }
}