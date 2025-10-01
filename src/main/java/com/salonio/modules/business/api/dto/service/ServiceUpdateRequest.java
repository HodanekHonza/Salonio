package com.salonio.modules.business.api.dto.service;

import jakarta.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.UUID;

public record ServiceUpdateRequest(
        String name,
        String description,
        BigDecimal price,
        Integer durationMinutes,
        Boolean isActive,
        @NotNull UUID businessId
) {
}
