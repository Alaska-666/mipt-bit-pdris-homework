package ru.mipt.bit.homework.weathercurrencyapp.entities;

import org.springframework.beans.factory.annotation.Configurable;
import ru.mipt.bit.homework.weathercurrencyapp.services.weather.Weather;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
@Configurable
public class WeatherEntity {
    @Id
    private String date;
    @Id
    private String city;
    private transient Weather weather;

    public WeatherEntity(String date, String city, Weather weather) {
        this.date = date;
        this.city = city;
        this.weather = weather;
    }

    public WeatherEntity() {}
}
