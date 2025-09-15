package com.salonio.availability.application.port.out;

import com.salonio.availability.domain.event.AvailabilitySlotConfirmedEvent;

public interface AvailabilityEventPort {
    void publishAvailabilitySlotConfirmedEvent(AvailabilitySlotConfirmedEvent event);
}
