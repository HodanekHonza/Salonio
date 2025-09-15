package com.salonio.modules.availability.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public record CreateAvailabilityRequest(
        @NotNull LocalDateTime startTime,
        @NotNull LocalDateTime endTime,
        @NotNull UUID staffId,
        @NotNull UUID businessId,
        @NotBlank boolean availability,
        @NotNull UUID bookingId,
        @NotNull UUID clientId
) {}