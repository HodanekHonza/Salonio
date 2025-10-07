package com.salonio.modules.business.api.dto.service;

import java.time.LocalDateTime;
import java.util.List;

public record ServiceErrorResponse(LocalDateTime timestamp,
                                   int status,
                                   String error,
                                   String message,
                                   String path,
                                   List<String> details
) {
    public ServiceErrorResponse(int status, String error, String message, String path) {
        this(LocalDateTime.now(), status, error, message, path, null);
    }
}