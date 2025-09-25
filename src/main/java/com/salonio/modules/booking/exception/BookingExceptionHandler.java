package com.salonio.modules.booking.exception;

import com.salonio.modules.booking.api.dto.BookingErrorResponse;
import com.salonio.modules.common.util.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import java.util.ConcurrentModificationException;

@RestControllerAdvice
public class BookingExceptionHandler {


    @ExceptionHandler(BookingExceptions.BookingNotFoundException.class)
    public ResponseEntity<BookingErrorResponse> handleBookingNotFound(
            BookingExceptions.BookingNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(BookingExceptions.BookingConflictException.class)
    public ResponseEntity<BookingErrorResponse> handleBookingConflict(
            BookingExceptions.BookingConflictException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ExceptionHandler(ConcurrentModificationException.class)
    public ResponseEntity<BookingErrorResponse> handleConcurrentModificationException(
            ConcurrentModificationException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BookingErrorResponse> handleGenericRuntimeException(
            RuntimeException ex, HttpServletRequest request) {
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred. Please try again later.",
                request
        );
    }

    private ResponseEntity<BookingErrorResponse> buildErrorResponse(
            HttpStatus status, String message, HttpServletRequest request) {

        String sanitizedMessage = SecurityUtils.sanitizeInput(message);
        String sanitizedUri = SecurityUtils.sanitizeInput(request.getRequestURI());

        BookingErrorResponse response = new BookingErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                sanitizedMessage,
                sanitizedUri
        );

        return new ResponseEntity<>(response, status);
    }

}
