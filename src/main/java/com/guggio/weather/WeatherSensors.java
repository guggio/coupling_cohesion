package com.guggio.weather;

import javax.annotation.Nonnull;
import java.util.Map;
import java.util.Optional;

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
  public WeatherData getWeatherData(@Nonnull String location) throws UnknownLocationException {
    return getOptionalDataString(location)
        .map(dataString -> mapToWeatherData(location, dataString))
        .orElseThrow(() -> new UnknownLocationException(String.format("No data available for %s!", location)));
  }

  @Nonnull
  private Optional<String> getOptionalDataString(@Nonnull String location) {
    return Optional.ofNullable(weatherData.get(location));
  }

  @Nonnull
  private WeatherData mapToWeatherData(@Nonnull String location, @Nonnull String dataString) {
    int separatorIndex = dataString.indexOf(" ");
    if (separatorIndex == -1) {
      throw new IllegalStateException(String.format("Data is corrupted for location %s", location));
    }
    double humidity = Double.parseDouble(dataString.substring(separatorIndex + 1));
    double celsius = Double.parseDouble(dataString.substring(0, separatorIndex));
    return new WeatherData(humidity, Temperature.ofCelsius(celsius)
    );
  }

}
