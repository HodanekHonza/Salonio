package com.salonio.modules.availability.exception;

import com.salonio.modules.availability.api.dto.AvailabilityErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class AvailabilityExceptionHandler {

    @ExceptionHandler(AvailabilityExceptions.AvailabilityNotFoundException.class)
    public ResponseEntity<AvailabilityErrorResponse> handleAvailabilityNotFound(
            AvailabilityExceptions.AvailabilityNotFoundException ex, HttpServletRequest request) {

        AvailabilityErrorResponse availabilityErrorResponse = new AvailabilityErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(availabilityErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(AvailabilityExceptions.AvailabilityConflictException.class)
    public ResponseEntity<AvailabilityErrorResponse> handleAvailabilityConflict(
            AvailabilityExceptions.AvailabilityConflictException ex, HttpServletRequest request) {

        AvailabilityErrorResponse availabilityErrorResponse = new AvailabilityErrorResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(availabilityErrorResponse, HttpStatus.CONFLICT);
    }
}
