package com.salonio.modules.business.api.dto.category.service;

import java.time.Instant;
import java.util.UUID;

public record ServiceCategoryResponse(
        UUID id,
        Integer version,
        String name,
        String description,
        boolean active,
        Instant createdAt,
        Instant updatedAt,
        String icon,
        String colorCode
) {
}
