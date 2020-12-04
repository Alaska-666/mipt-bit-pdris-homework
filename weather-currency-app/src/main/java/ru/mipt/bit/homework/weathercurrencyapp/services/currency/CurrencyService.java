package ru.mipt.bit.homework.weathercurrencyapp.services.currency;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

import org.dom4j.*;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

@Service
public class CurrencyService {
    private String getResponseFromUrl(String url) {
        ResponseEntity<String> response = new RestTemplate().getForEntity(url, String.class);
        return response.getBody();
    }

    private Double getDollarCurrencyDaysBefore(int daysBefore) {
        LocalDateTime now = LocalDateTime.now();
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("dd/MM/yyyy");
        String url = "http://www.cbr.ru/scripts/XML_daily.asp?date_req=" + dtf.format(now.minusDays(daysBefore));
        try {
            return getDollarValue(getResponseFromUrl(url));
        } catch (Exception ignored) {
            return 0.0;
        }
    }

    private Double getDollarValue(String body) throws DocumentException {
        List<Node> nodes = DocumentHelper.parseText(body).selectNodes("//Valute[@ID='" + Dollar.id + "']/Value");
        if (nodes.isEmpty()) {
            return 0.0;
        }
        return new Double(nodes.get(0).getText().replace(",", "."));
    }

    public List<Double> getDollarCurrency(int days) {
        return IntStream.range(0, days)
                .mapToObj(this::getDollarCurrencyDaysBefore)
                .collect(Collectors.toList());
    }
}
