package eu.profinit.manta.weathersystem.service;

import eu.profinit.manta.weathersystem.exception.CityNotFoundException;
import eu.profinit.manta.weathersystem.model.request.WeatherRequest;
import eu.profinit.manta.weathersystem.model.response.WeatherResponse;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Dummy implementation returning random weather data.
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
                getRandomEnum(WeatherResponse.CloudCoverage.class),
                getRandomIntInInterval(0, 45),
                getRandomIntInInterval(0, 360)
        );
    }

    @Override
    public WeatherResponse getForecast(WeatherRequest weatherRequest) {
        return null;
//        return new WeatherResponse(weatherRequest.getCity(), weatherRequest.getDateTime());
    }

    private int getRandomIntInInterval(double min, double max) {
        return random.nextInt((int) max - (int) min) + (int) min;
    }

    private <T extends Enum<?>> T getRandomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
