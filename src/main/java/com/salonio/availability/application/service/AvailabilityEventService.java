package com.salonio.availability.application.service;

import com.salonio.availability.application.port.out.AvailabilityEventPort;
import com.salonio.availability.domain.event.AvailabilitySlotConfirmedEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AvailabilityEventService implements AvailabilityEventPort {

    private final ApplicationEventPublisher applicationEventPublisher;

    @Override
    public void publishAvailabilitySlotConfirmedEvent(AvailabilitySlotConfirmedEvent event) {
        applicationEventPublisher.publishEvent(event);
    }

}
