package eu.profinit.manta.weathersystem.model.response;

import java.time.LocalDateTime;

public class WeatherResponse {

    private final String city;

    private final LocalDateTime dateTime;

    /**
     * Celsius degrees
     */
    private final double temperature;

    private final CloudCoverage cloudCoverage;

    /**
     * m/s
     */
    private final double windSpeed;

    /**
     * degrees
     */
    private final int windDirection;

    public WeatherResponse(String city, LocalDateTime dateTime, double temperature, CloudCoverage cloudCoverage, double windSpeed, int windDirection) {
        this.city = city;
        this.dateTime = dateTime;
        this.temperature = temperature;
        this.cloudCoverage = cloudCoverage;
        this.windSpeed = windSpeed;
        this.windDirection = windDirection;
    }

    public enum CloudCoverage {
        CLEAR,
        FEW,
        SCATTERED,
        BROKEN,
        OVERCAST
    }

    public String getCity() {
        return city;
    }

    public LocalDateTime getDateTime() {
        return dateTime;
    }

    public double getTemperature() {
        return temperature;
    }

    public CloudCoverage getCloudCoverage() {
        return cloudCoverage;
    }

    public double getWindSpeed() {
        return windSpeed;
    }

    public int getWindDirection() {
        return windDirection;
    }
}
