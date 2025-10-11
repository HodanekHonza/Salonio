package com.salonio.modules.availability.exception;

import com.salonio.modules.availability.api.dto.AvailabilityErrorResponse;
import com.salonio.modules.common.util.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotReadableException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.method.annotation.MethodArgumentTypeMismatchException;

@RestControllerAdvice
public class AvailabilityExceptionHandler {

    @ExceptionHandler(AvailabilityExceptions.AvailabilityNotFoundException.class)
    public ResponseEntity<AvailabilityErrorResponse> handleAvailabilityNotFound(
            AvailabilityExceptions.AvailabilityNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(AvailabilityExceptions.AvailabilityConflictException.class)
    public ResponseEntity<AvailabilityErrorResponse> handleAvailabilityConflict(
            AvailabilityExceptions.AvailabilityConflictException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<AvailabilityErrorResponse> handleGenericRuntimeException(
            RuntimeException ex, HttpServletRequest request) {
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred. Please try again later.",
                request
        );
    }

    private ResponseEntity<AvailabilityErrorResponse> buildErrorResponse(
            HttpStatus status, String message, HttpServletRequest request) {

        String sanitizedMessage = SecurityUtils.sanitizeInput(message);
        String sanitizedUri = SecurityUtils.sanitizeInput(request.getRequestURI());

        AvailabilityErrorResponse response = new AvailabilityErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                sanitizedMessage,
                sanitizedUri
        );

        return new ResponseEntity<>(response, status);
    }

    @ExceptionHandler(MethodArgumentTypeMismatchException.class)
    public ResponseEntity<AvailabilityErrorResponse> handleTypeMismatch(
            MethodArgumentTypeMismatchException ex, HttpServletRequest request) {

        String message = String.format("Invalid value '%s' for parameter '%s'. Expected type: %s",
                ex.getValue(), ex.getName(), ex.getRequiredType() != null ? ex.getRequiredType().getSimpleName() : "unknown");

        return buildErrorResponse(HttpStatus.BAD_REQUEST, message, request);
    }


    @ExceptionHandler(HttpMessageNotReadableException.class)
    public ResponseEntity<AvailabilityErrorResponse> handleInvalidRequestBody(
            HttpMessageNotReadableException ex, HttpServletRequest request) {

        return buildErrorResponse(HttpStatus.BAD_REQUEST, "Malformed request body: " + ex.getMessage(), request);
    }

}
