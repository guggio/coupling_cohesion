package com.guggio.weather;

import javax.annotation.Nonnull;

public class Temperature {

  private final double celsius;
  private final double fahrenheit;
  private final double kelvin;

  private Temperature(double celsius, double fahrenheit, double kelvin) {
    this.celsius = celsius;
    this.fahrenheit = fahrenheit;
    this.kelvin = kelvin;
  }

  @Nonnull
  public static Temperature ofCelsius(double celsius) {
    return new Temperature(
        celsius,
        TemperatureConverter.convertToFahrenheit(celsius),
        TemperatureConverter.convertToKelvin(celsius)
    );
  }

  public double getCelsius() {
    return celsius;
  }

  public double getFahrenheit() {
    return fahrenheit;
  }

  public double getKelvin() {
    return kelvin;
  }
}
