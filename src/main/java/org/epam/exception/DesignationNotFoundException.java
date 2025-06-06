package org.epam.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(value = HttpStatus.BAD_REQUEST)
public class DesignationNotFoundException extends RuntimeException{
    public DesignationNotFoundException(String message) {
        super(message);
    }
}
