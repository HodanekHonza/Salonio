package com.salonio.modules.availability.application.service;

import com.salonio.modules.availability.application.port.out.AvailabilityEventPort;
import com.salonio.modules.availability.domain.event.AvailabilitySlotConfirmedEvent;
import com.salonio.modules.common.event.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AvailabilityEventService implements AvailabilityEventPort {

    private final DomainEventPublisher publisher;

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityEventService.class);

    @Override
    public void publishAvailabilitySlotConfirmedEvent(AvailabilitySlotConfirmedEvent event) {
        logger.info("Publishing availability slot confirmed event");
        publisher.publish(event);
    }

}
