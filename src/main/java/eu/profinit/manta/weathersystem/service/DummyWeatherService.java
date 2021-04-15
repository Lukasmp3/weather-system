package eu.profinit.manta.weathersystem.service;

import eu.profinit.manta.weathersystem.exception.CityNotFoundException;
import eu.profinit.manta.weathersystem.model.request.WeatherRequest;
import eu.profinit.manta.weathersystem.model.response.WeatherResponse;
import eu.profinit.manta.weathersystem.model.response.WeatherResponse.CloudCoverage;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Dummy implementation returning random weather data.
 *
 * {@link #getHistory(WeatherRequest)} returns always {@link CloudCoverage#OVERCAST}.
 * {@link #getForecast(WeatherRequest)} returns always {@link CloudCoverage#CLEAR}.
 */
@Service
public class DummyWeatherService implements WeatherService {

    private final Random random = new Random();

    @Override
    public WeatherResponse getHistory(WeatherRequest weatherRequest) {

        String city = weatherRequest.getCity();
        if (city.isBlank()) {
            throw new CityNotFoundException(city);
        }

        return new WeatherResponse(
                city,
                weatherRequest.getDateTime(),
                getRandomIntInInterval(-20, 30),
                CloudCoverage.OVERCAST,
                getRandomIntInInterval(0, 45),
                getRandomIntInInterval(0, 360)
        );
    }

    @Override
    public WeatherResponse getForecast(WeatherRequest weatherRequest) {
        String city = weatherRequest.getCity();
        if (city.isBlank()) {
            throw new CityNotFoundException(city);
        }

        return new WeatherResponse(
                city,
                weatherRequest.getDateTime(),
                getRandomIntInInterval(-20, 30),
                CloudCoverage.CLEAR,
                getRandomIntInInterval(0, 45),
                getRandomIntInInterval(0, 360)
        );
    }

    private int getRandomIntInInterval(double min, double max) {
        return random.nextInt((int) max - (int) min) + (int) min;
    }

    private <T extends Enum<?>> T getRandomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
