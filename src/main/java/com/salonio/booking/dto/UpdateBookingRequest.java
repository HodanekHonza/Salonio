package com.salonio.booking.dto;

import com.salonio.booking.enums.BookingStatus;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public record UpdateBookingRequest(@NotNull
                                   UUID clientId,
                                   @NotNull UUID staffId,
                                   @NotNull
                                   LocalDateTime startTime,
                                   @NotNull
                                   LocalDateTime endTime,
                                   @NotBlank
                                   String serviceType,
                                   @NotNull
                                   BookingStatus status
) {
}
