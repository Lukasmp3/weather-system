package eu.profinit.manta.weathersystem.controller;

import eu.profinit.manta.weathersystem.model.request.WeatherRequest;
import eu.profinit.manta.weathersystem.model.response.WeatherResponse;
import eu.profinit.manta.weathersystem.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/weather/v1")
public class WeatherController {

    private final WeatherService weatherService;

    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/history")
    public ResponseEntity<WeatherResponse> getHistory(
            @RequestParam("city") String city,
            @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {

        return ResponseEntity.ok(weatherService.getHistory(new WeatherRequest(city, dateTime)));
    }

    @GetMapping("/forecast")
    public ResponseEntity<WeatherResponse> getForecast(
            @RequestParam("city") String city,
            @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {

        return ResponseEntity.ok(weatherService.getForecast(new WeatherRequest(city, dateTime)));
    }

}
