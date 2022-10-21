package com.guggio.weather;

import javax.annotation.Nonnull;

public class LegacyWeatherProvider {

  @Nonnull
  private final WeatherSensors weatherSensors;

  LegacyWeatherProvider() {
    weatherSensors = new WeatherSensors();
  }

  double getWeatherData(@Nonnull String location, int type) throws UnknownLocationException {
    double[] data = retrieveData(location);
    return switch (type) {
      case 0 -> data[0];
      case 1 -> data[1];
      default -> throw new IllegalArgumentException(String.format("Unknown type %d", type));
    };
  }

  private double[] retrieveData(@Nonnull String location) throws UnknownLocationException {
    if (weatherSensors.getWeatherData().containsKey(location)) {
      String data = weatherSensors.getWeatherData().get(location);
      int separatorIndex = data.indexOf(" ");
      if (separatorIndex == -1) {
        throw new IllegalStateException(String.format("Data is corrupted for location %s", location));
      }
      return new double[]{Double.parseDouble(data.substring(0, separatorIndex)), Double.parseDouble(data.substring(separatorIndex + 1))};
    } else {
      throw new UnknownLocationException(String.format("No data available for %s!", location));
    }
  }
}
