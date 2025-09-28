package com.salonio.modules.business.api.dto.category.business;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BusinessCategoryCreateRequest(@NotNull UUID businessId,
                                            @NotNull @NotBlank String textForm) {
}
