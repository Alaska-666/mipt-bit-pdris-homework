package ru.mipt.bit.homework.weathercurrencyapp.controllers;

import java.util.List;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import ru.mipt.bit.homework.weathercurrencyapp.services.currency.CurrencyService;

@RestController
public class CurrencyController {
    final CurrencyService currencyService;

    public CurrencyController(CurrencyService currencyService) {
        this.currencyService = currencyService;
    }

    @GetMapping("/currency")
    public List<Double> getCurrency(@RequestParam(required=false) Integer days) {
        if (days == null) {
            days = 1;
        }
        return currencyService.getDollarCurrency(days);
    }
}
