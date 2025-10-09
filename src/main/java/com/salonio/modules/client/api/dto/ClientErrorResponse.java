package com.salonio.modules.client.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public record ClientErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<String> details
) {
    public ClientErrorResponse(int status, String error, String message, String path) {
        this(LocalDateTime.now(), status, error, message, path, null);
    }
}