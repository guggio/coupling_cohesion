package com.guggio.weather;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.EnumSource;

import javax.annotation.Nonnull;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

class WeatherAppTest {

  private WeatherApp weatherApp;

  @BeforeEach
  void setUp() {
    weatherApp = WeatherApp.of();
  }

  @ParameterizedTest
  @EnumSource(LocationData.class)
  void shouldReturnTemperatureInCelsiusForLocation(@Nonnull LocationData locationData) throws UnknownLocationException {
    TemperatureRequest request = TemperatureRequest.ofCelsius(locationData.getLocation());
    assertEquals(locationData.getCelsius(), weatherApp.getTemperature(request), 2f);
  }

  @ParameterizedTest
  @EnumSource(LocationData.class)
  void shouldReturnTemperatureInFahrenheitForLocation(@Nonnull LocationData locationData) throws UnknownLocationException {
    TemperatureRequest request = TemperatureRequest.ofFahrenheit(locationData.getLocation());
    assertEquals(locationData.getFahrenheit(), weatherApp.getTemperature(request), 2f);
  }

  @ParameterizedTest
  @EnumSource(LocationData.class)
  void shouldReturnTemperatureInKelvinForLocation(@Nonnull LocationData locationData) throws UnknownLocationException {
    TemperatureRequest request = TemperatureRequest.ofKelvin(locationData.getLocation());
    assertEquals(locationData.getKelvin(), weatherApp.getTemperature(request), 2f);
  }

  @ParameterizedTest
  @EnumSource(LocationData.class)
  void shouldReturnHumidityForLocation(@Nonnull LocationData locationData) throws UnknownLocationException {
    assertEquals(locationData.getHumidity(), weatherApp.getHumidity(locationData.getLocation()));
  }

  @Test
  void shouldThrowUnknownLocationExceptionWhileGettingTemperatureForUnknownLocation() {
    TemperatureRequest request = TemperatureRequest.ofCelsius("Solothurn");
    UnknownLocationException exception = assertThrows(UnknownLocationException.class, () -> weatherApp.getTemperature(request));
    assertEquals("No data available for Solothurn!", exception.getMessage());
  }

  @Test
  void shouldThrowUnknownLocationExceptionWhileGettingHumidityForUnknownLocation() {
    UnknownLocationException exception = assertThrows(UnknownLocationException.class, () -> weatherApp.getHumidity("Solothurn"));
    assertEquals("No data available for Solothurn!", exception.getMessage());
  }

  private enum LocationData {
    BERN("Bern", 35.7d, new Temperature(20.0d, 68.0d, 293.15d)),
    ZURICH("Zurich", 40.0d, new Temperature(19.5d, 67.1d, 292.65d)),
    ST_GALLEN("St. Gallen", 85.0d, new Temperature(14.3d, 57.74d, 287.45d)),
    LOCARNO("Locarno", 20.4d, new Temperature(23.2d, 73.76d, 296.35d));

    private final String location;
    private final double humidity;
    private final Temperature temperature;

    LocationData(String location, double humidity, Temperature temperature) {
      this.location = location;
      this.temperature = temperature;
      this.humidity = humidity;
    }

    public String getLocation() {
      return location;
    }

    public double getCelsius() {
      return temperature.celsius();
    }

    public double getFahrenheit() {
      return temperature.fahrenheit();
    }

    public double getKelvin() {
      return temperature.kelvin();
    }

    public double getHumidity() {
      return humidity;
    }
  }

  private record Temperature(double celsius, double fahrenheit, double kelvin) {
  }

}