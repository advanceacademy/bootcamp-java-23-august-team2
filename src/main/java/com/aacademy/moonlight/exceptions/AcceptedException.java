package com.aacademy.moonlight.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

@ResponseStatus(HttpStatus.ACCEPTED)
public class AcceptedException extends RuntimeException {
    public AcceptedException(String message) {
        super(message);
    }
}
