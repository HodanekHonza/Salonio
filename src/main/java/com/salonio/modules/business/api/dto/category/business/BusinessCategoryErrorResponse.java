package com.salonio.modules.business.api.dto.category.business;

import java.time.LocalDateTime;
import java.util.List;

public record BusinessCategoryErrorResponse(LocalDateTime timestamp,
                                            int status,
                                            String error,
                                            String message,
                                            String path,
                                            List<String> details
) {
    public BusinessCategoryErrorResponse(int status, String error, String message, String path) {
        this(LocalDateTime.now(), status, error, message, path, null);
    }
}