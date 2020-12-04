package ru.mipt.bit.homework.weathercurrencyapp.controllers;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import ru.mipt.bit.homework.weathercurrencyapp.services.PredictService;

@RestController
public class PredictController {
    private final PredictService predictService;

    public PredictController(PredictService predictService) {
        this.predictService = predictService;
    }

    @GetMapping("/predict")
    public Double getPredictedDollarExchangeRate() {
        return predictService.getPredictedDollarExchangeRate();
    }
}
