package com.salonio.modules.availability.application.factory;

import com.salonio.modules.availability.domain.event.AvailabilitySlotConfirmedEvent;
import java.util.UUID;

public final class AvailabilityEventFactory {

    private AvailabilityEventFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static AvailabilitySlotConfirmedEvent create(UUID bookingId) {
        return new AvailabilitySlotConfirmedEvent(
                bookingId
        );
    }

}
