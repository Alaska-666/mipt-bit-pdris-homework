package ru.mipt.bit.homework.weathercurrencyapp.controllers;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.ResultActions;
import org.springframework.test.web.servlet.request.MockHttpServletRequestBuilder;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import ru.mipt.bit.homework.weathercurrencyapp.services.weather.WeatherService;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
public class WeatherControllerTest {
    @Autowired
    private WeatherController weatherController;

    @Test
    void getWeather() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/weather");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.weatherController).build().perform(requestBuilder);
        resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        resultActions.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(1));
    }

    @Test
    void getWeatherNullDays() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/weather?days=0");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.weatherController).build().perform(requestBuilder);
        resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        resultActions.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }

    @Test
    void getWeatherMinusDays() throws Exception {
        MockHttpServletRequestBuilder requestBuilder = MockMvcRequestBuilders.get("/weather?days=-9");
        ResultActions resultActions = MockMvcBuilders.standaloneSetup(this.weatherController).build().perform(requestBuilder);
        resultActions.andExpect(MockMvcResultMatchers.status().is2xxSuccessful());
        resultActions.andExpect(MockMvcResultMatchers.content().contentType("application/json"));
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$").isArray());
        resultActions.andExpect(MockMvcResultMatchers.jsonPath("$.length()").value(0));
    }
}