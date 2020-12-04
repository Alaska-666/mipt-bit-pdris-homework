package ru.mipt.bit.homework.weathercurrencyapp.entities;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class CurrencyEntity {
    @Id
    private String date;

    private double rate;

    public CurrencyEntity(String date, double rate) {
        this.date = date;
        this.rate = rate;
    }

    public CurrencyEntity() {}

    public double getRate() {
        return rate;
    }

    public String getData() {
        return date;
    }
}
