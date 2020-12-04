package ru.mipt.bit.homework.weathercurrencyapp.entities;

import org.springframework.beans.factory.annotation.Configurable;
import ru.mipt.bit.homework.weathercurrencyapp.services.weather.Weather;

import javax.persistence.*;

@Entity
@Configurable
public class WeatherEntity {
    @EmbeddedId
    private WeatherEntityId id;
    @OneToOne(cascade= CascadeType.ALL)
    private Weather weather;

    public WeatherEntity(WeatherEntityId id, Weather weather) {
        this.id = id;
        this.weather = weather;
    }

    public WeatherEntity() {}

    public Weather getWeather() {
        return weather;
    }

    public WeatherEntityId getId() {
        return id;
    }
}
