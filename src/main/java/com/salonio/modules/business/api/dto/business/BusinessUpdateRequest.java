package com.salonio.modules.business.api.dto.business;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record BusinessUpdateRequest(@NotNull UUID businessId,
                                    @NotNull @NotBlank String textForm) {
}
