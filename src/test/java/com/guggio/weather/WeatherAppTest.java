package com.guggio.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeatherAppTest {

  private WeatherApp weatherApp;

  @BeforeEach
  void setUp() {
    weatherApp = new WeatherApp();
  }

  @ParameterizedTest
  @EnumSource(LocationData.class)
  void shouldReturnTemperatureForLocation(LocationData locationData) throws UnknownLocationException {
    assertEquals(locationData.getTemperature(), weatherApp.getTemperature(locationData.getLocation()));
  }

  @ParameterizedTest
  @EnumSource(LocationData.class)
  void shouldReturnHumidityForLocation(LocationData locationData) throws UnknownLocationException {
    assertEquals(locationData.getHumidity(), weatherApp.getHumidity(locationData.getLocation()));
  }

  @Test
  void shouldThrowUnknownLocationExceptionWhileGettingTemperatureForUnknownLocation() {
    UnknownLocationException exception = assertThrows(UnknownLocationException.class, () -> weatherApp.getTemperature("Solothurn"));
    assertEquals("No data available for Solothurn!", exception.getMessage());
  }

  @Test
  void shouldThrowUnknownLocationExceptionWhileGettingHumidityForUnknownLocation() {
    UnknownLocationException exception = assertThrows(UnknownLocationException.class, () -> weatherApp.getHumidity("Solothurn"));
    assertEquals("No data available for Solothurn!", exception.getMessage());
  }

  private enum LocationData {
    BERN("Bern", 20.0d, 35.7d),
    ZURICH("Zurich", 19.5d, 40.0d),
    ST_GALLEN("St. Gallen", 14.3d, 85.0d),
    LOCARNO("Locarno", 23.2d, 20.4d);

    private final String location;
    private final double temperature;
    private final double humidity;

    LocationData(String location, double temperature, double humidity) {
      this.location = location;
      this.temperature = temperature;
      this.humidity = humidity;
    }

    public String getLocation() {
      return location;
    }

    public double getTemperature() {
      return temperature;
    }

    public double getHumidity() {
      return humidity;
    }
  }

}