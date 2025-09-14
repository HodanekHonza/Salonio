package com.salonio.booking.api.dto;

import com.salonio.booking.domain.enums.BookingStatus;

import java.time.LocalDateTime;
import java.util.UUID;

public record BookingResponse(UUID id, LocalDateTime startTime, LocalDateTime endTime, UUID clientId, UUID staffId,
                              String serviceType, BookingStatus status) {
}