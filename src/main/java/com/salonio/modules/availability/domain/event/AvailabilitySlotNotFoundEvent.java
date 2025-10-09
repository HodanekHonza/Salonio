package com.salonio.modules.availability.domain.event;

import java.util.UUID;

public record AvailabilitySlotNotFoundEvent(
        UUID bookingId
) {
}
