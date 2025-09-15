package com.salonio.availability.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public record AvailabilityErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<String> details
) {
    public AvailabilityErrorResponse(int status, String error, String message, String path) {
        this(LocalDateTime.now(), status, error, message, path, null);
    }
}
