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
    WeatherData weatherData = weatherSensors.getWeatherData(request.getLocation());
    return request.getTemperatureSelector().apply(weatherData.temperature());
  }

  @Override
  public double getHumidity(@Nonnull String location) throws UnknownLocationException {
    return weatherSensors.getWeatherData(location).humidity();
  }

}
