package eu.profinit.manta.weathersystem.model.request;

import java.text.DateFormat;
import java.time.LocalDateTime;

public class CommonWeatherRequest {

    private final String city;

    private final LocalDateTime dateTime;

    public CommonWeatherRequest(String city, LocalDateTime dateTime) {
        this.city = city;
        this.dateTime = dateTime;
    }

    public String getCity() {
        return city;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }
}
