package com.salonio.modules.business.api.dto.service;

import jakarta.validation.constraints.DecimalMin;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.Digits;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.Max;
import java.math.BigDecimal;
import java.util.UUID;

public record ServiceCreateRequest(

        @NotBlank(message = "Service name cannot be blank.")
        @Size(min = 2, max = 100, message = "Service name must be between 2 and 100 characters.")
        String name,

        @NotBlank(message = "Description cannot be blank.")
        @Size(max = 500, message = "Description cannot exceed 500 characters.")
        String description,

        @NotNull(message = "Price cannot be null.")
        @DecimalMin(value = "0.0", inclusive = false, message = "Price must be greater than 0.")
        @Digits(integer = 8, fraction = 2, message = "Price must have up to 8 digits and 2 decimals.")
        BigDecimal price,

        @NotNull(message = "Duration cannot be null.")
        @Min(value = 5, message = "Duration must be at least 5 minutes.")
        @Max(value = 480, message = "Duration cannot exceed 480 minutes (8 hours).")
        Integer durationMinutes,

        @NotNull(message = "Service status must be specified.")
        Boolean isActive,

        @NotNull(message = "Business ID must be provided.")
        UUID businessId

) {
}
