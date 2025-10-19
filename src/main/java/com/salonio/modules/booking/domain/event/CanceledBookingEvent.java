package com.salonio.modules.booking.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record CanceledBookingEvent(

        UUID staffId,

        LocalDateTime startTime,

        LocalDateTime endTime

) {
}
