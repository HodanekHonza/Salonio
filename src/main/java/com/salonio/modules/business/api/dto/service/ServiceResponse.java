package com.salonio.modules.business.api.dto.service;

import java.math.BigDecimal;
import java.util.UUID;

public record ServiceResponse(
        UUID id,
        Integer version,
        String name,
        String description,
        BigDecimal price,
        Integer durationMinutes,
        Boolean isActive,
        UUID businessId
) {
}
