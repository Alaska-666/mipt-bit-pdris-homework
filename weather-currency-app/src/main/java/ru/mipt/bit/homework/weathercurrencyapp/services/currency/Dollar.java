package ru.mipt.bit.homework.weathercurrencyapp.services.currency;

public class Dollar {
    public static final String ID = "R01235";
    public static final int NUM_CODE = 840;
    public static final String CHAR_CODE = "USD";
    public static final int NOMINAL = 1;
    public static final String NAME = "Доллар США";

    private Dollar() {
        throw new IllegalStateException("Utility class");
    }
}
