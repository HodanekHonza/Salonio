package com.salonio.modules.availability.application.factory;

import com.salonio.modules.availability.domain.event.AvailabilitySchedulingEvent;
import com.salonio.modules.availability.domain.event.AvailabilitySlotCanceledEvent;
import com.salonio.modules.availability.domain.event.AvailabilitySlotConfirmedEvent;

import java.util.UUID;

public final class AvailabilityEventFactory {

    private AvailabilityEventFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static AvailabilitySlotConfirmedEvent createAvailabilitySlotConfirmedEvent(UUID bookingId) {
        return new AvailabilitySlotConfirmedEvent(
                bookingId
        );
    }

    public static AvailabilitySlotCanceledEvent createAvailabilitySlotCanceledEvent(UUID bookingId) {
        return new AvailabilitySlotCanceledEvent(
                bookingId
        );
    }

    public static AvailabilitySchedulingEvent createAvailabilitySchedulingEvent(boolean isScheduling) {
        return new AvailabilitySchedulingEvent(
                isScheduling
        );
    }

}
