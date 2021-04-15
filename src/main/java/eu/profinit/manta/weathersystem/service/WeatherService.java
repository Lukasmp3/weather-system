package eu.profinit.manta.weathersystem.service;

import eu.profinit.manta.weathersystem.model.request.WeatherRequest;
import eu.profinit.manta.weathersystem.model.response.WeatherResponse;

/**
 * New external systems or databases should implement this interface.
 */
public interface WeatherService {

    WeatherResponse getHistory(WeatherRequest weatherRequest);

    WeatherResponse getForecast(WeatherRequest weatherRequest);

}
