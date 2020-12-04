package ru.mipt.bit.homework.weathercurrencyapp.entities;

import org.springframework.beans.factory.annotation.Configurable;
import ru.mipt.bit.homework.weathercurrencyapp.services.weather.Weather;

import javax.persistence.*;

@Entity
@Configurable
@IdClass(WeatherEntityId.class)
public class WeatherEntity {
    @Id
    private String date;
    @Id
    private String city;

    @OneToOne(cascade= CascadeType.ALL)
    private Weather weather;

    public WeatherEntity(String date, String city, Weather weather) {
        this.date = date;
        this.city = city;
        this.weather = weather;
    }

    public WeatherEntity() {}

    public Weather getWeather() {
        return weather;
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    @Override
    public String toString() {
        return "WeatherEntity{" +
                "date='" + date + '\'' +
                ", city='" + city + '\'' +
                ", weather=" + weather +
                '}';
    }
}
