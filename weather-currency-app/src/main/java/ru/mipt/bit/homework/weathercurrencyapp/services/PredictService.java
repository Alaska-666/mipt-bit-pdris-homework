package ru.mipt.bit.homework.weathercurrencyapp.services;

import org.apache.commons.math3.stat.regression.SimpleRegression;
import org.springframework.stereotype.Service;
import ru.mipt.bit.homework.weathercurrencyapp.services.currency.CurrencyService;
import ru.mipt.bit.homework.weathercurrencyapp.services.weather.Weather;
import ru.mipt.bit.homework.weathercurrencyapp.services.weather.WeatherService;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class PredictService {
    private final WeatherService weatherService;
    private final CurrencyService currencyService;
    private static final int NUMBER_OF_DAYS_TO_PREDICT = 8;
    private static final String CITY_TO_PREDICT = "Moscow";

    public PredictService(WeatherService weatherService, CurrencyService currencyService) {
        this.weatherService = weatherService;
        this.currencyService = currencyService;
    }

    private void addData(SimpleRegression regression, List<Weather> weatherData, List<Double> currencyData) {
        IntStream.range(0, NUMBER_OF_DAYS_TO_PREDICT)
                .forEach(i -> regression.addData(weatherData.get(i).getAvgTempC(), currencyData.get(i)));
    }

    public Double getPredictedDollarExchangeRate() {
        SimpleRegression regression = new SimpleRegression();
        List<Weather> weatherData = weatherService.getWeather(NUMBER_OF_DAYS_TO_PREDICT, CITY_TO_PREDICT);
        List<Double> currencyData = currencyService.getDollarCurrency(NUMBER_OF_DAYS_TO_PREDICT);
        addData(regression, weatherData, currencyData);
        return regression.predict(weatherData.get(0).getAvgTempC());
    }

    public WeatherService getWeatherService() {
        return weatherService;
    }

    public CurrencyService getCurrencyService() {
        return currencyService;
    }

}
