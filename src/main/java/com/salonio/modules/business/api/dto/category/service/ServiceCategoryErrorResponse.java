package com.salonio.modules.business.api.dto.category.service;

import java.time.LocalDateTime;
import java.util.List;

public record ServiceCategoryErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<String> details
) {
    public ServiceCategoryErrorResponse(int status, String error, String message, String path) {
        this(LocalDateTime.now(), status, error, message, path, null);
    }
}