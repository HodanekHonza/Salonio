package com.salonio.modules.business.api.dto.service;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.util.UUID;

public record ServiceCreateRequest(@NotNull UUID businessId,
                                   @NotNull @NotBlank String textForm) {
}
