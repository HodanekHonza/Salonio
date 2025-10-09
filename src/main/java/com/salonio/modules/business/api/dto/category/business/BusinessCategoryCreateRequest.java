package com.salonio.modules.business.api.dto.category.business;

import com.salonio.modules.business.domain.enums.BusinessCategory;
import jakarta.validation.constraints.NotNull;

public record BusinessCategoryCreateRequest(
        @NotNull BusinessCategory businessCategory
) {
}
