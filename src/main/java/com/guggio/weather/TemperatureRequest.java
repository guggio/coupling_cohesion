package com.guggio.weather;

import javax.annotation.Nonnull;
import java.util.function.Function;

/**
 * The fields must not be changed.
 */
public class TemperatureRequest {

  private static final int CELSIUS_UNIT = 0;
  private static final int FAHRENHEIT_UNIT = 1;
  private static final int KELVIN_UNIT = 2;

  private final int unit;
  @Nonnull private final String location;

  private TemperatureRequest(@Nonnull String location, int unit) {
    this.unit = unit;
    this.location = location;
  }

  @Nonnull
  public static TemperatureRequest ofCelsius(@Nonnull String location) {
    return new TemperatureRequest(location, CELSIUS_UNIT);
  }

  @Nonnull
  public static TemperatureRequest ofFahrenheit(@Nonnull String location) {
    return new TemperatureRequest(location, FAHRENHEIT_UNIT);
  }

  @Nonnull
  public static TemperatureRequest ofKelvin(@Nonnull String location) {
    return new TemperatureRequest(location, KELVIN_UNIT);
  }

  @Nonnull
  public String getLocation() {
    return location;
  }

  @Nonnull
  public Function<Temperature, Double> getTemperatureSelector() {
    return switch (unit) {
      case CELSIUS_UNIT -> Temperature::getCelsius;
      case FAHRENHEIT_UNIT -> Temperature::getFahrenheit;
      case KELVIN_UNIT -> Temperature::getKelvin;
      default -> throw new IllegalStateException(String.format("No temperature selector defined for unit %d", unit));
    };
  }

}
