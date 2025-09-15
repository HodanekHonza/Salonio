package com.salonio.availability.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateAvailabilityRequest(@NotNull
                                   UUID clientId,
                                        @NotNull UUID staffId,
                                        @NotNull
                                   LocalDateTime startTime,
                                        @NotNull
                                   LocalDateTime endTime,
                                        @NotBlank
                                   String serviceType
) {
}
