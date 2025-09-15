package com.salonio.modules.availability.application.service;

import com.salonio.modules.availability.application.port.out.AvailabilityEventPort;
import com.salonio.modules.availability.domain.event.AvailabilitySlotConfirmedEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Component;

@AllArgsConstructor
@Component
public class AvailabilityEventService implements AvailabilityEventPort {

    private final ApplicationEventPublisher applicationEventPublisher;

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityEventService.class);

    @Override
    public void publishAvailabilitySlotConfirmedEvent(AvailabilitySlotConfirmedEvent event) {
        logger.info("Publishing availability slot confirmed event");
        applicationEventPublisher.publishEvent(event);
    }

}
