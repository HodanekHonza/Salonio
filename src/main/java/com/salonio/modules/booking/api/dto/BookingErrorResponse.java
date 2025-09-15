package com.salonio.modules.booking.api.dto; // Or another common package

import java.time.LocalDateTime;
import java.util.List;

public record BookingErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<String> details
) {
    public BookingErrorResponse(int status, String error, String message, String path) {
        this(LocalDateTime.now(), status, error, message, path, null);
    }
}