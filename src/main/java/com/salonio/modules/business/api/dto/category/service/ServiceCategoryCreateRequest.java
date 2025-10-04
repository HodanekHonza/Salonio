package com.salonio.modules.business.api.dto.category.service;

import java.time.Instant;

public record ServiceCategoryCreateRequest(
        String name,
        String description,
        boolean active,
        Instant createdAt,
        Instant updatedAt,
        String icon,
        String colorCode
) {
}
