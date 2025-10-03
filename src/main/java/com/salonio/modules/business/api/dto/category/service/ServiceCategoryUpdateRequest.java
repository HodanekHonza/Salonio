package com.salonio.modules.business.api.dto.category.service;

import jakarta.validation.constraints.NotNull;

import java.time.Instant;
import java.util.UUID;

public record ServiceCategoryUpdateRequest(
        @NotNull  String name,
        @NotNull String description,
        int sortOrder,
        boolean active,
        Instant createdAt,
        Instant updatedAt,
        UUID parentCategoryId,
        String icon,
        String colorCode
) {
}
