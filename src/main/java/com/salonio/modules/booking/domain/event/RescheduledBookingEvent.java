package com.salonio.modules.booking.domain.event;

import java.time.LocalDateTime;
import java.util.UUID;

public record RescheduledBookingEvent(

        UUID staffId,

        LocalDateTime originalStartTime,

        LocalDateTime originalEndTime,

        LocalDateTime newStartTime,

        LocalDateTime newEndTime

) {
}
