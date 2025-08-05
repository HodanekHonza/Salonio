// src/main/java/com/salonio/common/dto/ErrorResponse.java (or com.salonio.api.dto.ErrorResponse)
package com.salonio.booking.dto; // Or another common package

import java.time.LocalDateTime;
import java.util.List;

public record ErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<String> details // Optional: for validation errors, etc.
) {
    public ErrorResponse(int status, String error, String message, String path) {
        this(LocalDateTime.now(), status, error, message, path, null);
    }
}