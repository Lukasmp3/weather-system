package eu.profinit.manta.weathersystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

// TODO: I could also use ExceptionHandler for whole app at one place
@ResponseStatus(value= HttpStatus.NOT_FOUND, reason="No such city")
public class CityNotFoundException extends RuntimeException {

    public CityNotFoundException(String name) {
        super("Could not find city with name=" + name);
    }
}
