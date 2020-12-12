package ru.mipt.bit.homework.weathercurrencyapp.services.currency;

public class Dollar {
    public final static String id = "R01235";
    public final static int numCode = 840;
    public final static String charCode = "USD";
    public final static int nominal = 1;
    public final static String name = "Доллар США";

    private Dollar() {
        throw new IllegalStateException("Utility class");
    }
}
