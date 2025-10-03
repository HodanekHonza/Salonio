package com.salonio.modules.business.api.dto.category.business;

import com.salonio.modules.business.domain.BusinessCategory;
import jakarta.validation.constraints.NotNull;

public record BusinessCategoryUpdateRequest(
        @NotNull BusinessCategory businessCategory,
        Integer numberOfBusinesses
) {
}
