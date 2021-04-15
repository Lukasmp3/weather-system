package eu.profinit.manta.weathersystem.service;

import eu.profinit.manta.weathersystem.model.request.CommonWeatherRequest;
import eu.profinit.manta.weathersystem.model.response.CommonWeatherResponse;
import org.springframework.stereotype.Service;

import java.util.Random;

/**
 * Dummy implementation returning random weather data.
 */
@Service
public class DummyWeatherService implements WeatherService {

    private final Random random = new Random();

    @Override
    public CommonWeatherResponse getHistory(CommonWeatherRequest weatherRequest) {
        return new CommonWeatherResponse(
                weatherRequest.getCity(),
                weatherRequest.getDateTime(),
                getRandomIntInInterval(-20, 30),
                getRandomEnum(CommonWeatherResponse.CloudCoverage.class),
                getRandomIntInInterval(0, 45),
                getRandomIntInInterval(0, 360)
        );
    }

    @Override
    public CommonWeatherResponse getForecast(CommonWeatherRequest weatherRequest) {
        return null;
//        return new CommonWeatherResponse(weatherRequest.getCity(), weatherRequest.getDateTime());
    }

    private int getRandomIntInInterval(double min, double max) {
        return random.nextInt((int) max - (int) min) + (int) min;
    }

    private <T extends Enum<?>> T getRandomEnum(Class<T> clazz){
        int x = random.nextInt(clazz.getEnumConstants().length);
        return clazz.getEnumConstants()[x];
    }
}
