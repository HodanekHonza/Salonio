package com.salonio.modules.common.dto;

public record ErrorResponse(
        int status,
        String error,
        String message,
        String path
) {
}
