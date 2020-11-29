package ru.mipt.bit.homework.weathercurrencyapp.services.weather;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

@Entity
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    public Double maxtemp_c;
    public Double mintemp_c;
    public Double avgtemp_c;

    public Double maxwind_mph;
    public Double totalprecip_mm;
    public Double avgvis_km;

    @Override
    public String toString() {
        return "Weather{" +
                "maxtemp_c=" + maxtemp_c +
                ", mintemp_c=" + mintemp_c +
                ", avgtemp_c=" + avgtemp_c +
                ", maxwind_mph=" + maxwind_mph +
                ", totalprecip_mm=" + totalprecip_mm +
                ", avgvis_km=" + avgvis_km + '\'' +
                '}';
    }
}
