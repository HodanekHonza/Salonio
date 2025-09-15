package com.salonio.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

public class BookingExceptions {

    public static class BookingException extends RuntimeException {
        public BookingException(String message) {
            super(message);
        }

        public BookingException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.NOT_FOUND)
    public static class BookingNotFoundException extends BookingException {
        public BookingNotFoundException(String message) {
            super(message);
        }
    }

    @ResponseStatus(HttpStatus.CONFLICT)
    public static class BookingConflictException extends BookingException {
        public BookingConflictException(String message) {
            super(message);
        }

        public BookingConflictException(String message, Throwable cause) {
            super(message, cause);
        }
    }

    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public static class BookingDeletionException extends BookingException {
        public BookingDeletionException(String message, Throwable cause) {
            super(message, cause);
        }
    }

}
