package com.salonio.modules.business.api.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ReviewUpdateRequest(@NotNull UUID businessId,
                                  @NotNull @NotBlank String textForm) {
}
