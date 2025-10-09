package com.salonio.modules.staff.api.dto;

import java.time.LocalDateTime;
import java.util.List;

public record StaffErrorResponse(
        LocalDateTime timestamp,
        int status,
        String error,
        String message,
        String path,
        List<String> details
) {
    public StaffErrorResponse(int status, String error, String message, String path) {
        this(LocalDateTime.now(), status, error, message, path, null);
    }
}