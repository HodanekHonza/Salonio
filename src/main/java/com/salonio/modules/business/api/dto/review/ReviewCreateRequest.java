package com.salonio.modules.business.api.dto.review;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.util.UUID;

public record ReviewCreateRequest(

        @NotNull
        @NotBlank
        String textForm,

        @NotNull
        UUID clientId,

        @NotNull
        UUID businessId,

        @Min(value = 0, message = "Rating must be at least 0.")
        @Max(value = 5, message = "Rating must be at most 5.")
        @NotNull
        Integer rating

) {
}
