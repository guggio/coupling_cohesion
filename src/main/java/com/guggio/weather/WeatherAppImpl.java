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
    Celsius celsius = weatherSensors.getTemperature(request.getLocation());
    return request.getUnit().fromCelsius(celsius);
  }

  @Override
  public double getHumidity(@Nonnull String location) throws UnknownLocationException {
    return weatherSensors.getHumitidy(location);
  }

}
