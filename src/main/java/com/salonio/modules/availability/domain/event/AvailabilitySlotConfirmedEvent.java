package com.salonio.modules.availability.domain.event;

import java.util.UUID;

public record AvailabilitySlotConfirmedEvent(
        UUID bookingId
) {
}
