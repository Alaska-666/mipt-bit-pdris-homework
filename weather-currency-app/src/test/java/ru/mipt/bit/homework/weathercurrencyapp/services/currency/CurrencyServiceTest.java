package ru.mipt.bit.homework.weathercurrencyapp.services.currency;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class CurrencyServiceTest {
    @Autowired
    private CurrencyService currencyService;

    @Test
    public void currencyForDays() {
        List<Double> result = currencyService.getDollarCurrency(2);
        assertEquals(2, result.size());
        result.forEach(i -> assertTrue(70 <= i && i <= 80));
    }

    @Test
    public void currencyForManyDays() {
        List<Double> result = currencyService.getDollarCurrency(100);
        assertEquals(100, result.size());
        result.forEach(i -> assertTrue(70 <= i && i <= 85));
    }

    @Test
    public void currencyForNullDays() {
        List<Double> result = currencyService.getDollarCurrency(0);
        assertTrue(result.isEmpty());
    }

    @Test
    public void currencyForMinusDays() {
        List<Double> result = currencyService.getDollarCurrency(-9);
        assertTrue(result.isEmpty());
    }
}