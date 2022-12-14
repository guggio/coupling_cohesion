package com.guggio.weather;

import javax.annotation.Nonnull;
import java.util.Map;

class WeatherSensors {

  /**
   * The map must not be changed
   */
  @Nonnull private final Map<String, String> weatherData = Map.of(
      "Bern", "20.0 35.7",
      "Zurich", "19.5 40.0",
      "St. Gallen", "14.3 85.0",
      "Locarno", "23.2 20.4"
  );

  @Nonnull
  Map<String, String> getWeatherData() {
    return weatherData;
  }

}
