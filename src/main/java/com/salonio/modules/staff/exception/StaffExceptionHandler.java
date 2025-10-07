package com.salonio.modules.staff.exception;

import com.salonio.modules.common.util.SecurityUtils;
import com.salonio.modules.staff.api.dto.StaffErrorResponse;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class StaffExceptionHandler {

    @ExceptionHandler(StaffExceptions.StaffNotFoundException.class)
    public ResponseEntity<StaffErrorResponse> handleAvailabilityNotFound(
            StaffExceptions.StaffNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(StaffExceptions.StaffConflictException.class)
    public ResponseEntity<StaffErrorResponse> handleAvailabilityConflict(
            StaffExceptions.StaffConflictException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<StaffErrorResponse> handleGenericRuntimeException(
            RuntimeException ex, HttpServletRequest request) {
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred. Please try again later.",
                request
        );
    }

    private ResponseEntity<StaffErrorResponse> buildErrorResponse(
            HttpStatus status, String message, HttpServletRequest request) {

        String sanitizedMessage = SecurityUtils.sanitizeInput(message);
        String sanitizedUri = SecurityUtils.sanitizeInput(request.getRequestURI());

        StaffErrorResponse response = new StaffErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                sanitizedMessage,
                sanitizedUri
        );

        return new ResponseEntity<>(response, status);
    }

}

