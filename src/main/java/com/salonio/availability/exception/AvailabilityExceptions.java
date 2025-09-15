package com.salonio.availability.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class AvailabilityExceptions {

    public static class AvailabilityException extends RuntimeException {
        public AvailabilityException(String message) {
            super(message);
        }

        public AvailabilityException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class AvailabilityNotFoundException extends AvailabilityExceptions.AvailabilityException {
        public AvailabilityNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class AvailabilityConflictException extends AvailabilityExceptions.AvailabilityException {
        public AvailabilityConflictException(String message) {
            super(message);
        }

        public AvailabilityConflictException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class AvailabilityDeletionException extends AvailabilityExceptions.AvailabilityException {
        public AvailabilityDeletionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
