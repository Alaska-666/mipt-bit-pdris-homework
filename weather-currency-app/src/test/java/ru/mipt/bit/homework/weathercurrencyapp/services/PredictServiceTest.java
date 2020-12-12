package ru.mipt.bit.homework.weathercurrencyapp.services;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import ru.mipt.bit.homework.weathercurrencyapp.services.currency.CurrencyService;
import ru.mipt.bit.homework.weathercurrencyapp.services.weather.WeatherService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class PredictServiceTest {
    @Autowired
    private CurrencyService currencyService;
    @Autowired
    private WeatherService weatherService;
    @Autowired
    private PredictService predictService;

    @Test
    public void testConstructor() {
       PredictService newPredictService = new PredictService(weatherService, currencyService);
       assertNotNull(newPredictService.getCurrencyService());
       assertNotNull(newPredictService.getWeatherService());
    }

    @Test
    public void testPredict() {
        Double predictedRate = predictService.getPredictedDollarExchangeRate();
        assertTrue(70 <= predictedRate && predictedRate <= 80);
    }
}