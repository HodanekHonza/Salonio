package com.salonio.modules.business.api.dto.category.business;

import com.salonio.modules.business.domain.enums.BusinessCategory;
import java.util.UUID;

public record BusinessCategoryResponse(
        UUID id,
        BusinessCategory textForm
) {
}
