package com.salonio.modules.availability.application.port.out;

import com.salonio.modules.availability.domain.event.AvailabilitySlotCanceledEvent;
import com.salonio.modules.availability.domain.event.AvailabilitySlotConfirmedEvent;

public interface AvailabilityEventPort {

    void publishAvailabilitySlotConfirmedEvent(AvailabilitySlotConfirmedEvent event);

    void publishAvailabilitySlotCanceledEvent(AvailabilitySlotCanceledEvent event);


}
