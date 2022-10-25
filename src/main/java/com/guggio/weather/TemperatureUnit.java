/*
 * TemperatureUnit.java
 *
 * Creator:
 * 24.10.22 14:16 guggisbergs
 *
 * Maintainer:
 * 24.10.22 14:16 guggisbergs
 *
 * Last Modification:
 * $Id: $
 *
 * Copyright (c) 2022 ABACUS Research AG, All Rights Reserved
 */
package com.guggio.weather;

import java.util.Arrays;
import java.util.function.Function;

enum TemperatureUnit {

  CELSIUS(0, Celsius::celsius),
  FAHRENHEIT(1, TemperatureUnit::convertToFahrenheit),
  KELVIN(2, TemperatureUnit::convertToKelvin);

  private static double convertToKelvin(Celsius temp) {
    return temp.celsius() + 273.15d;
  }

  private static double convertToFahrenheit(Celsius temp) {
    return temp.celsius() * (9d / 5d) + 32d;
  }

  private final int id;
  private final Function<Celsius, Double> fromCelsiusConverter;

  TemperatureUnit(int id, Function<Celsius, Double> fromCelsiusConverter) {
    this.id = id;
    this.fromCelsiusConverter = fromCelsiusConverter;
  }

  public double fromCelsius(Celsius celsius) {
    return fromCelsiusConverter.apply(celsius);
  }

  public int getId() {
    return id;
  }

  public static TemperatureUnit valueOf(int id) {
    return Arrays.stream(values())
        .filter(unit -> unit.getId() == id)
        .findFirst()
        .orElseThrow();
  }
}

