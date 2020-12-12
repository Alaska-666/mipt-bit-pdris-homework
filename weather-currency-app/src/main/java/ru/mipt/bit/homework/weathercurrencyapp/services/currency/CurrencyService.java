package ru.mipt.bit.homework.weathercurrencyapp.services.currency;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.dom4j.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;
import ru.mipt.bit.homework.weathercurrencyapp.entities.CurrencyEntity;
import ru.mipt.bit.homework.weathercurrencyapp.repositories.CurrencyRepository;

@Service
public class CurrencyService {
    @Autowired
    private CurrencyRepository currencyRepository;

    private String getResponseFromUrl(String url) {
        ResponseEntity<String> response = new RestTemplate().getForEntity(url, String.class);
        return response.getBody();
    }

    private Double getDollarCurrencyDaysBefore(int daysBefore) {
        String date = DateTimeFormatter.ofPattern("dd/MM/yyyy").format(LocalDate.now().minusDays(daysBefore));
        Optional<CurrencyEntity> currency = currencyRepository.findById(date);
        return currency.map(CurrencyEntity::getRate).orElseGet(() -> getDollarValue(date));
    }

    private Double getDollarValue(String date) {
        String urlPattern = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=";
        String body = getResponseFromUrl(urlPattern + date);
        List<Node> nodes;
        try {
            nodes = DocumentHelper.parseText(body).selectNodes("//Valute[@ID='" + Dollar.ID + "']/Value");
        } catch (DocumentException ignored) {
            return 0.0;
        }

        if (nodes.isEmpty()) {
            return 0.0;
        }
        Double rate = new Double(nodes.get(0).getText().replace(",", "."));
        currencyRepository.save(new CurrencyEntity(date, rate));
        return rate;
    }

    public List<Double> getDollarCurrency(int days) {
        return IntStream.range(0, days)
                .mapToObj(this::getDollarCurrencyDaysBefore)
                .collect(Collectors.toList());
    }
}
