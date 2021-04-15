package eu.profinit.manta.weathersystem.controller;

import eu.profinit.manta.weathersystem.model.request.WeatherRequest;
import eu.profinit.manta.weathersystem.model.response.WeatherResponse;
import eu.profinit.manta.weathersystem.service.WeatherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.time.LocalDateTime;

@RestController
@RequestMapping("/weather/v1")
public class WeatherController {

    private WeatherService weatherService;

    @Autowired
    public WeatherController(WeatherService weatherService) {
        this.weatherService = weatherService;
    }

    @GetMapping("/history")
    public WeatherResponse getHistory(
            @RequestParam("city") String city,
            @RequestParam("dateTime") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME) LocalDateTime dateTime) {

        WeatherResponse weatherResponse = weatherService.getHistory(new WeatherRequest(city, dateTime));

        return weatherResponse;
    }

}
