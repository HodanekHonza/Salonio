package com.salonio.modules.business.exception.category.business;

import com.salonio.modules.business.api.dto.category.business.BusinessCategoryErrorResponse;
import com.salonio.modules.common.util.SecurityUtils;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class BusinessCategoryExceptionHandler {

    @ExceptionHandler(BusinessCategoryExceptions.BusinessCategoryNotFoundException.class)
    public ResponseEntity<BusinessCategoryErrorResponse> handleAvailabilityNotFound(
            BusinessCategoryExceptions.BusinessCategoryNotFoundException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.NOT_FOUND, ex.getMessage(), request);
    }

    @ExceptionHandler(BusinessCategoryExceptions.BusinessCategoryConflictException.class)
    public ResponseEntity<BusinessCategoryErrorResponse> handleAvailabilityConflict(
            BusinessCategoryExceptions.BusinessCategoryConflictException ex, HttpServletRequest request) {
        return buildErrorResponse(HttpStatus.CONFLICT, ex.getMessage(), request);
    }

    @ExceptionHandler(RuntimeException.class)
    public ResponseEntity<BusinessCategoryErrorResponse> handleGenericRuntimeException(
            RuntimeException ex, HttpServletRequest request) {
        return buildErrorResponse(
                HttpStatus.INTERNAL_SERVER_ERROR,
                "An unexpected error occurred. Please try again later.",
                request
        );
    }

    private ResponseEntity<BusinessCategoryErrorResponse> buildErrorResponse(
            HttpStatus status, String message, HttpServletRequest request) {

        String sanitizedMessage = SecurityUtils.sanitizeInput(message);
        String sanitizedUri = SecurityUtils.sanitizeInput(request.getRequestURI());

        BusinessCategoryErrorResponse response = new BusinessCategoryErrorResponse(
                status.value(),
                status.getReasonPhrase(),
                sanitizedMessage,
                sanitizedUri
        );

        return new ResponseEntity<>(response, status);
    }

}
