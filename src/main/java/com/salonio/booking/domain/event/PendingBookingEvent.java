package com.salonio.booking.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class PendingBookingEvent {

    private UUID bookingId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private UUID staffId;
    private UUID clientId;

}
