package ru.mipt.bit.homework.weathercurrencyapp.entities;

import javax.persistence.Embeddable;
import java.io.Serializable;
import java.util.Objects;

@Embeddable
public class WeatherEntityId implements Serializable {
    private String date;
    private String city;

    public WeatherEntityId(String date, String city) {
        this.date = date;
        this.city = city;
    }

    public WeatherEntityId() {
    }

    public String getDate() {
        return date;
    }

    public String getCity() {
        return city;
    }

    public void setDate(String date) {
        this.date = date;
    }

    public void setCity(String city) {
        this.city = city;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        WeatherEntityId that = (WeatherEntityId) o;
        return date.equals(that.date) &&
                city.equals(that.city);
    }

    @Override
    public int hashCode() {
        return Objects.hash(date, city);
    }
}
