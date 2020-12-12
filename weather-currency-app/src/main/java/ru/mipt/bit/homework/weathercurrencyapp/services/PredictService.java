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
    private static final int numberOfDaysToPredict = 8;
    private static final String cityToPredict = "Moscow";

    public PredictService(WeatherService weatherService, CurrencyService currencyService) {
        this.weatherService = weatherService;
        this.currencyService = currencyService;
    }

    private void addData(SimpleRegression regression, List<Weather> weatherData, List<Double> currencyData) {
        IntStream.range(0, numberOfDaysToPredict)
                .forEach(i -> regression.addData(weatherData.get(i).getAvgTempC(), currencyData.get(i)));
    }

    public Double getPredictedDollarExchangeRate() {
        SimpleRegression regression = new SimpleRegression();
        List<Weather> weatherData = weatherService.getWeather(numberOfDaysToPredict, cityToPredict);
        List<Double> currencyData = currencyService.getDollarCurrency(numberOfDaysToPredict);
        addData(regression, weatherData, currencyData);
        return regression.predict(weatherData.get(0).getAvgTempC());
    }
}
