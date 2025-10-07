package com.salonio.modules.business.exception.category.service;

import com.salonio.modules.business.api.dto.category.service.ServiceCategoryErrorResponse;
import com.salonio.modules.common.util.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ServiceCategoryExceptionHandler {

    @ExceptionHandler(ServiceCategoryExceptions.ServiceCategoryNotFoundException.class)
    public ResponseEntity<ServiceCategoryErrorResponse> handleAvailabilityNotFound(
            ServiceCategoryExceptions.ServiceCategoryNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(ServiceCategoryExceptions.ServiceCategoryConflictException.class)
    public ResponseEntity<ServiceCategoryErrorResponse> handleAvailabilityConflict(
            ServiceCategoryExceptions.ServiceCategoryConflictException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<ServiceCategoryErrorResponse> handleGenericRuntimeException(
            RuntimeException ex, HttpServletRequest request) {
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred. Please try again later.",
                request
        );
    }

    private ResponseEntity<ServiceCategoryErrorResponse> buildErrorResponse(
            HttpStatus status, String message, HttpServletRequest request) {

        String sanitizedMessage = SecurityUtils.sanitizeInput(message);
        String sanitizedUri = SecurityUtils.sanitizeInput(request.getRequestURI());

        ServiceCategoryErrorResponse response = new ServiceCategoryErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                sanitizedMessage,
                sanitizedUri
        );

        return new ResponseEntity<>(response, status);
    }

}