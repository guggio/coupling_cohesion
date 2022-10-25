package com.guggio.weather;

import javax.annotation.Nonnull;

/**
 * The fields must not be changed.
 */
public class TemperatureRequest {

  private final TemperatureUnit unit;
  @Nonnull private final String location;

  private TemperatureRequest(@Nonnull String location, int unit) {
    this.unit = TemperatureUnit.valueOf(unit);
    this.location = location;
  }

  @Nonnull
  public static TemperatureRequest ofCelsius(@Nonnull String location) {
    return new TemperatureRequest(location, 0);
  }

  @Nonnull
  public static TemperatureRequest ofFahrenheit(@Nonnull String location) {
    return new TemperatureRequest(location, 1);
  }

  @Nonnull
  public static TemperatureRequest ofKelvin(@Nonnull String location) {
    return new TemperatureRequest(location, 2);
  }

  @Nonnull
  public String getLocation() {
    return location;
  }

  public TemperatureUnit getUnit() {
    return unit;
  }
}
