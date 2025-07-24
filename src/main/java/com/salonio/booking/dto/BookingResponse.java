package com.salonio.booking.dto;

import com.salonio.booking.enums.BookingStatus;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

public record BookingResponse(UUID id, LocalDateTime startTime, Duration duration, UUID clientId, UUID staffId,
                              String serviceType, BookingStatus status) {
}