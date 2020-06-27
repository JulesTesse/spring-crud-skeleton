package com.extia.cinema.exceptions;

import org.springframework.http.HttpStatus;
import org.springframework.web.server.ResponseStatusException;

public class GlobalCinemaException extends ResponseStatusException {
    public GlobalCinemaException(HttpStatus status) {
        super(status);
    }

    public GlobalCinemaException(HttpStatus status, String reason) {
        super(status, reason);
    }

    public GlobalCinemaException(HttpStatus status, String reason, Throwable cause) {
        super(status, reason, cause);
    }
}
