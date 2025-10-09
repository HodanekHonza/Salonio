package com.salonio.modules.availability.api.dto;

import java.time.LocalDateTime;
import java.util.UUID;

public record AvailabilityResponse(
        UUID id,
        LocalDateTime startTime,
        LocalDateTime endTime,
        UUID staffId,
        UUID businessId,
        boolean availability,
        UUID bookingId,
        UUID clientId
) {
}
