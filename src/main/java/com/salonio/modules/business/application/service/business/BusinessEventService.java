package com.salonio.modules.business.application.service.business;

import com.salonio.modules.business.application.port.business.out.BusinessEventPort;
import com.salonio.modules.business.domain.event.business.BusinessSchedulingEvent;
import com.salonio.modules.common.event.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class BusinessEventService implements BusinessEventPort {

    private final DomainEventPublisher publisher;

    private static final Logger logger = LoggerFactory.getLogger(BusinessEventService.class);

    @Override
    public void publishBusinessSchedulingEvent(BusinessSchedulingEvent event) {
        logger.info("Publishing business scheduling event");
        publisher.publish(event);
    }

}
