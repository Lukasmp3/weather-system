package eu.profinit.manta.weathersystem.service;

import eu.profinit.manta.weathersystem.model.request.CommonWeatherRequest;
import eu.profinit.manta.weathersystem.model.response.CommonWeatherResponse;
import org.springframework.stereotype.Service;

@Service
public interface WeatherService {

    CommonWeatherResponse getHistory(CommonWeatherRequest weatherRequest);

    CommonWeatherResponse getForecast(CommonWeatherRequest weatherRequest);

}
