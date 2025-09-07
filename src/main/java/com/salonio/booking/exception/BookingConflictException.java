package com.salonio.booking.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * Custom exception to be thrown when a booking creation fails due to a conflict,
 * such as an optimistic locking failure or a duplicate entry.
 * It maps to an HTTP 409 Conflict status.
 */
@ResponseStatus(HttpStatus.CONFLICT)
public class BookingConflictException extends RuntimeException {

    // Constructor that accepts a message
    public BookingConflictException(String message) {
        super(message);
    }

    // Constructor that accepts a message and the original cause
    // This is useful for preserving the original exception chain
    public BookingConflictException(String message, Throwable cause) {
        super(message, cause);
    }
}