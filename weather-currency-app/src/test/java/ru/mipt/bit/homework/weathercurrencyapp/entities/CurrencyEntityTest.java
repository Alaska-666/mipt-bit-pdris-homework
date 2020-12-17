package ru.mipt.bit.homework.weathercurrencyapp.entities;

import org.junit.jupiter.api.Test;

import javax.xml.crypto.Data;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

import static org.junit.jupiter.api.Assertions.*;

class CurrencyEntityTest {

    @Test
    void getRate() {
        CurrencyEntity currencyEntity = new CurrencyEntity(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now().minusDays(7)), 100);
        assertEquals(100, currencyEntity.getRate());
    }

    @Test
    void getData() {
        CurrencyEntity currencyEntity = new CurrencyEntity(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now()), 100);
        assertEquals(DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now()), currencyEntity.getData());
    }
}