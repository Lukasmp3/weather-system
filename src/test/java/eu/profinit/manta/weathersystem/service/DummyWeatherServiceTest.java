package eu.profinit.manta.weathersystem.service;

import eu.profinit.manta.weathersystem.exception.CityNotFoundException;
import eu.profinit.manta.weathersystem.exception.InvalidDateTimeException;
import eu.profinit.manta.weathersystem.model.request.WeatherRequest;
import eu.profinit.manta.weathersystem.model.response.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

@SpringBootTest
public class DummyWeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @Test
    public void dummyWeatherServiceHistoryValid() {
        LocalDateTime dateTime = LocalDateTime.MIN;
        WeatherRequest weatherRequest = new WeatherRequest("Prague", dateTime);

        WeatherResponse weatherResponse = weatherService.getHistory(weatherRequest);

        assertThat(weatherResponse).isNotNull();
        assertThat(weatherResponse.getCity()).isEqualTo("Prague");
        assertThat(weatherResponse.getDateTime()).isEqualTo(dateTime);
        assertThat(weatherResponse.getTemperature()).isNotNull();
        assertThat(weatherResponse.getCloudCoverage()).isEqualTo(WeatherResponse.CloudCoverage.OVERCAST);
        assertThat(weatherResponse.getWindSpeed()).isNotNegative();
        assertThat(weatherResponse.getWindDirection()).isBetween(0, 360);
    }

    @Test
    public void dummyWeatherServiceHistoryCityNotFound() {
        LocalDateTime dateTime = LocalDateTime.MIN;

        WeatherRequest weatherRequest = new WeatherRequest("", dateTime);

        assertThrows(CityNotFoundException.class, () -> weatherService.getHistory(weatherRequest));
    }

    @Test
    public void dummyWeatherServiceHistoryFutureTime() {
        LocalDateTime dateTime = LocalDateTime.MAX;

        WeatherRequest weatherRequest = new WeatherRequest("Prague", dateTime);

        assertThrows(InvalidDateTimeException.class, () -> weatherService.getHistory(weatherRequest));
    }

    @Test
    public void dummyWeatherServiceForecastValid() {
        LocalDateTime dateTime = LocalDateTime.MAX;
        WeatherRequest weatherRequest = new WeatherRequest("Prague", dateTime);

        WeatherResponse weatherResponse = weatherService.getForecast(weatherRequest);

        assertThat(weatherResponse).isNotNull();
        assertThat(weatherResponse.getCity()).isEqualTo("Prague");
        assertThat(weatherResponse.getDateTime()).isEqualTo(dateTime);
        assertThat(weatherResponse.getTemperature()).isNotNull();
        assertThat(weatherResponse.getCloudCoverage()).isEqualTo(WeatherResponse.CloudCoverage.CLEAR);
        assertThat(weatherResponse.getWindSpeed()).isNotNegative();
        assertThat(weatherResponse.getWindDirection()).isBetween(0, 360);
    }

    @Test
    public void dummyWeatherServiceForecastCityNotFound() {
        LocalDateTime dateTime = LocalDateTime.MAX;

        WeatherRequest weatherRequest = new WeatherRequest("", dateTime);

        assertThrows(CityNotFoundException.class, () -> weatherService.getForecast(weatherRequest));
    }

    @Test
    public void dummyWeatherServiceForecastPastTime() {
        LocalDateTime dateTime = LocalDateTime.MIN;

        WeatherRequest weatherRequest = new WeatherRequest("Prague", dateTime);

        assertThrows(InvalidDateTimeException.class, () -> weatherService.getForecast(weatherRequest));
    }

}
