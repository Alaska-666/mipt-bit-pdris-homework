package ru.mipt.bit.homework.weathercurrencyapp.services.weather;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import com.fasterxml.jackson.annotation.JsonProperty;

@Entity
@JsonIgnoreProperties(ignoreUnknown = true)
public class Weather {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private int id;
    @JsonProperty("maxtemp_c")
    private Double maxTempC;
    @JsonProperty("mintemp_c")
    private Double minTempC;
    @JsonProperty("avgtemp_c")
    private Double avgTempC;

    @JsonProperty("maxwind_mph")
    private Double maxWindMph;
    @JsonProperty("totalprecip_mm")
    private Double totalPrecipMm;
    @JsonProperty("avgvis_km")
    private Double avgVisKm;

    @Override
    public String toString() {
        return "Weather{" +
                "maxTempC=" + maxTempC +
                ", minTempC=" + minTempC +
                ", avgTempC=" + avgTempC +
                ", maxWindMph=" + maxWindMph +
                ", totalPrecipMm=" + totalPrecipMm +
                ", avgVisKm=" + avgVisKm +
                '}';
    }

    public Double getMaxTempC() {
        return maxTempC;
    }

    public Double getMinTempC() {
        return minTempC;
    }

    public Double getAvgTempC() {
        return avgTempC;
    }

    public Double getMaxWindMph() {
        return maxWindMph;
    }

    public Double getTotalPrecipMm() {
        return totalPrecipMm;
    }

    public Double getAvgVisKm() {
        return avgVisKm;
    }
}
