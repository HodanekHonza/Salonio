package com.salonio.modules.booking.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record PendingBookingEvent(
        UUID bookingId,
        LocalDateTime startTime,
        LocalDateTime endTime,
        UUID staffId,
        UUID clientId
) {
}
