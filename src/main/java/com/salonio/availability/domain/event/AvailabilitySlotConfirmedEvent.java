package com.salonio.availability.domain.event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.time.LocalDateTime;
import java.util.UUID;

@Getter
@Setter
@AllArgsConstructor
public class AvailabilitySlotConfirmedEvent {

    private UUID bookingId;
}
