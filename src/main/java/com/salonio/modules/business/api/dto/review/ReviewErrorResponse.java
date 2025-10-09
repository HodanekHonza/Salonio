package com.salonio.modules.business.api.dto.review;

import java.time.LocalDateTime;
import java.util.List;

public record ReviewErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<String> details
) {
    public ReviewErrorResponse(int status, String error, String message, String path) {
        this(LocalDateTime.now(), status, error, message, path, null);
    }
}