package com.guggio.weather;

import javax.annotation.Nonnull;

/**
 * This interface and the signatures of the methods must not be changed!
 */
public interface WeatherApp {

  double getTemperature(@Nonnull TemperatureRequest request) throws UnknownLocationException;

  double getHumidity(@Nonnull String location) throws UnknownLocationException;

  @Nonnull
  static WeatherApp of() {
    return new WeatherAppImpl();
  }
}
