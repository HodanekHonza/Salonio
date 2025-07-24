package com.salonio.booking.dto;

import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;
import java.util.UUID;

public record GetBookingStaffRequest(
        @NotNull UUID staffId,
        @NotNull LocalDateTime startTime
) {
}
