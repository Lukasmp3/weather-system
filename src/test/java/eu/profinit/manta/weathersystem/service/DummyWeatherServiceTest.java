package eu.profinit.manta.weathersystem.service;

import eu.profinit.manta.weathersystem.model.request.CommonWeatherRequest;
import eu.profinit.manta.weathersystem.model.response.CommonWeatherResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class DummyWeatherServiceTest {

    @Autowired
    private WeatherService weatherService;

    @Test
    public void dummyWeatherServiceTest() {
        LocalDateTime dateTime = LocalDateTime.of(1999, 1, 1, 0, 0);
        CommonWeatherRequest weatherRequest = new CommonWeatherRequest("Prague", dateTime);

        CommonWeatherResponse weatherResponse = weatherService.getHistory(weatherRequest);

        assertThat(weatherResponse).isNotNull();
        assertThat(weatherResponse.getCity()).isEqualTo("Prague");
        assertThat(weatherResponse.getDateTime()).isEqualTo(dateTime);
        assertThat(weatherResponse.getTemperature()).isNotNull();
        assertThat(weatherResponse.getCloudCoverage()).isNotNull();
        assertThat(weatherResponse.getWindSpeed()).isNotNegative();
        assertThat(weatherResponse.getWindDirection()).isBetween(0, 360);
    }

}
