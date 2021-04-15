package eu.profinit.manta.weathersystem.service;

import eu.profinit.manta.weathersystem.model.request.CommonWeatherRequest;
import eu.profinit.manta.weathersystem.model.response.CommonWeatherResponse;
import org.springframework.stereotype.Service;

/**
 * Dummy implementation returning random weather data.
 */
@Service
public class DummyWeatherService implements WeatherService {

    @Override
    public CommonWeatherResponse getHistory(CommonWeatherRequest weatherRequest) {
        return null;
    }

    @Override
    public CommonWeatherResponse getForecast(CommonWeatherRequest weatherRequest) {
        return null;
    }
}
