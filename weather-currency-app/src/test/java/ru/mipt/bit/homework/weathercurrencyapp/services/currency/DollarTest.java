package ru.mipt.bit.homework.weathercurrencyapp.services.currency;

import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.Test;

public class DollarTest {
    @Test
    public void testGetParams() {
        assertEquals("R01235", Dollar.ID);
        assertEquals(840, Dollar.NUM_CODE);
        assertEquals("USD", Dollar.CHAR_CODE);
        assertEquals(1, Dollar.NOMINAL);
        assertEquals("Доллар США", Dollar.NAME);
    }
}