package eu.profinit.manta.weathersystem.controller;

import eu.profinit.manta.weathersystem.model.response.WeatherResponse;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;

import java.net.MalformedURLException;
import java.net.URL;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class WeatherControllerIntegrationTest {

    @LocalServerPort
    private int port;

    private URL base;

    @Autowired
    private TestRestTemplate template;

    @Test
    public void getHistoryValid() throws MalformedURLException {
        this.base = new URL("http://localhost:" + port + "/weather/v1/history?city=Prague&dateTime=2007-12-03T10:15");

        ResponseEntity<WeatherResponse> response = template.getForEntity(base.toString(), WeatherResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        WeatherResponse weatherResponse = response.getBody();
        assertThat(weatherResponse).isNotNull();
        assertThat(weatherResponse.getCity()).isEqualTo("Prague");
        assertThat(weatherResponse.getDateTime()).isEqualTo(LocalDateTime.of(2007, 12, 3, 10, 15));
        assertThat(weatherResponse.getTemperature()).isNotNull();
        assertThat(weatherResponse.getCloudCoverage()).isNotNull();
        assertThat(weatherResponse.getWindSpeed()).isNotNegative();
        assertThat(weatherResponse.getWindDirection()).isBetween(0, 360);
    }

    @Test
    public void getHistoryCityNotFound() throws MalformedURLException {
        this.base = new URL("http://localhost:" + port + "/weather/v1/history?city=&dateTime=2007-12-03T10:15");

        ResponseEntity<WeatherResponse> response = template.getForEntity(base.toString(), WeatherResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }

    @Test
    public void getForecastValid() throws MalformedURLException {
        this.base = new URL("http://localhost:" + port + "/weather/v1/forecast?city=Prague&dateTime=2030-12-03T10:15");

        ResponseEntity<WeatherResponse> response = template.getForEntity(base.toString(), WeatherResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.OK);
        WeatherResponse weatherResponse = response.getBody();
        assertThat(weatherResponse).isNotNull();
        assertThat(weatherResponse.getCity()).isEqualTo("Prague");
        assertThat(weatherResponse.getDateTime()).isEqualTo(LocalDateTime.of(2030, 12, 3, 10, 15));
        assertThat(weatherResponse.getTemperature()).isNotNull();
        assertThat(weatherResponse.getCloudCoverage()).isNotNull();
        assertThat(weatherResponse.getWindSpeed()).isNotNegative();
        assertThat(weatherResponse.getWindDirection()).isBetween(0, 360);
    }

    @Test
    public void getForecastCityNotFound() throws MalformedURLException {
        this.base = new URL("http://localhost:" + port + "/weather/v1/forecast?city=&dateTime=2007-12-03T10:15");

        ResponseEntity<WeatherResponse> response = template.getForEntity(base.toString(), WeatherResponse.class);

        assertThat(response.getStatusCode()).isEqualTo(HttpStatus.NOT_FOUND);
    }
}