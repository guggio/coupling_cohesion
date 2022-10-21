package com.guggio.weather;

import javax.annotation.Nonnull;

class WeatherAppImpl implements WeatherApp {

  @Nonnull
  private final WeatherSensors weatherSensors;

  WeatherAppImpl() {
    weatherSensors = new WeatherSensors();
  }

  @Override
  public double getTemperature(@Nonnull TemperatureRequest request) throws UnknownLocationException {
    return getWeatherData(request.getLocation(), 0, request.getUnit());
  }

  @Override
  public double getHumidity(@Nonnull String location) throws UnknownLocationException {
    return getWeatherData(location, 1, 0);
  }

  private double getWeatherData(@Nonnull String location, int type, int temperatureUnit) throws UnknownLocationException {
    double[] data = retrieveData(location);
    return switch (type) {
      case 0 -> getTemperature(temperatureUnit, data);
      case 1 -> data[1];
      default -> throw new IllegalArgumentException(String.format("Unknown type %d", type));
    };
  }

  private double getTemperature(int temperatureUnit, double[] data) {
    return switch (temperatureUnit) {
      case 0 -> data[0];
      case 1 -> data[0] * (9d / 5d) + 32d;
      case 2 -> data[0] + 273.15d;
      default -> throw new IllegalArgumentException(String.format("Unknown temperatureUnit %d", temperatureUnit));
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
