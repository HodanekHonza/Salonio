package com.salonio.modules.business.api.dto.review;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ReviewCreateRequest(
        @NotNull @NotBlank String textForm,
        @NotNull UUID clientId,
        @NotNull UUID businessId,
        @NotNull Integer rating
) {
}
