package com.guggio.weather;

public class WeatherApp {

  public double getTemperature(String location) throws UnknownLocationException {
    return new LegacyWeatherProvider().getWeatherData(location, 0);
  }

  public double getHumidity(String location) throws UnknownLocationException {
    return new LegacyWeatherProvider().getWeatherData(location, 1);
  }
}
