package com.salonio.booking.exception;

import com.salonio.booking.api.dto.BookingErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BookingExceptionHandler {

    @ExceptionHandler(BookingExceptions.BookingNotFoundException.class)
    public ResponseEntity<BookingErrorResponse> handleBookingNotFound(
            BookingExceptions.BookingNotFoundException ex, HttpServletRequest request) {

        BookingErrorResponse bookingErrorResponse = new BookingErrorResponse(
                HttpStatus.NOT_FOUND.value(),
                HttpStatus.NOT_FOUND.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(bookingErrorResponse, HttpStatus.NOT_FOUND);
    }

    @ExceptionHandler(BookingExceptions.BookingConflictException.class)
    public ResponseEntity<BookingErrorResponse> handleBookingConflict(
            BookingExceptions.BookingConflictException ex, HttpServletRequest request) {

        BookingErrorResponse bookingErrorResponse = new BookingErrorResponse(
                HttpStatus.CONFLICT.value(),
                HttpStatus.CONFLICT.getReasonPhrase(),
                ex.getMessage(),
                request.getRequestURI()
        );

        return new ResponseEntity<>(bookingErrorResponse, HttpStatus.CONFLICT);
    }
}
