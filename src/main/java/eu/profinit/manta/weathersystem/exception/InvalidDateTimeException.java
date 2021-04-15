package eu.profinit.manta.weathersystem.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.time.LocalDateTime;

@ResponseStatus(value= HttpStatus.UNPROCESSABLE_ENTITY, reason="Date time is not allowed for particular service")
public class InvalidDateTimeException extends RuntimeException {

    public InvalidDateTimeException(LocalDateTime dateTime, String serviceName) {
        super("Could not run service=" + serviceName + " with dateTime=" + dateTime);
    }
}
