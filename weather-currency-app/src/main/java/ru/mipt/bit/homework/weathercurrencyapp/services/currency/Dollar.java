package ru.mipt.bit.homework.weathercurrencyapp.services.currency;

public class Dollar {
    public static final String id = "R01235";
    public static final int numCode = 840;
    public static final String charCode = "USD";
    public static final int nominal = 1;
    public static final String name = "Доллар США";

    private Dollar() {
        throw new IllegalStateException("Utility class");
    }
}
