package com.guggio.weather;

public class TemperatureConverter {

  private TemperatureConverter() {
    throw new IllegalStateException("Utility class must not be instantiated!");
  }

  public static double convertToFahrenheit(double celsius) {
    return celsius * (9d / 5d) + 32d;
  }

  public static double convertToKelvin(double celsius) {
    return celsius + 273.15d;
  }
}
