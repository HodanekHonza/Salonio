package com.salonio.modules.business.api.dto.business;

import java.time.LocalDateTime;
import java.util.List;

public record BusinessErrorResponse(LocalDateTime timestamp,
                                    int status,
                                    String error,
                                    String message,
                                    String path,
                                    List<String> details
) {
    public BusinessErrorResponse(int status, String error, String message, String path) {
        this(LocalDateTime.now(), status, error, message, path, null);
    }
}