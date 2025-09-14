package com.salonio.booking.api.dto;

import com.salonio.booking.domain.enums.BookingStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;


import java.time.LocalDateTime;
import java.util.UUID;

public record CreateBookingRequest(
        @NotNull UUID clientId,
        @NotNull UUID staffId,
        @NotNull LocalDateTime startTime,
        @NotNull LocalDateTime endTime,
        @NotBlank String serviceType, // TODO @NotNull as well?
        @NotNull BookingStatus status
) {}
