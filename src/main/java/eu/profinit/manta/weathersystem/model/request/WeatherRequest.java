package eu.profinit.manta.weathersystem.model.request;

import java.time.LocalDateTime;

// TODO: In future, I could use separate models for controller and service layers
public class WeatherRequest {

    private final String city;

    private final LocalDateTime dateTime;

    public WeatherRequest(String city, LocalDateTime dateTime) {
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
