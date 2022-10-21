package com.guggio.weather;

import javax.annotation.Nonnull;
import java.util.Map;

public class WeatherSensors {

  @Nonnull
  private final Map<String, String> weatherData = Map.of(
      "Bern", "20.0 35.7",
      "Zurich", "19.5 40.0",
      "St. Gallen", "14.3 85.0",
      "Locarno", "23.2 20.4"
  );

  @Nonnull
  public Map<String, String> getWeatherData() {
    return weatherData;
  }

}
