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

  public Celsius getTemperature(String location) throws UnknownLocationException {
    return new Celsius(retrieveData(location) [0]);
  }

  public double getHumitidy(String location) throws UnknownLocationException {
    return retrieveData(location) [1];
  }

  private double[] retrieveData(@Nonnull String location) throws UnknownLocationException {
    if (weatherData.containsKey(location)) {
      String data = weatherData.get(location);
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
