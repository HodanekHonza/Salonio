package com.salonio.modules.business.application.service.business;

import com.salonio.modules.availability.domain.event.AvailabilitySchedulingEvent;
import com.salonio.modules.business.application.factory.business.BusinessEventFactory;
import com.salonio.modules.business.application.port.business.out.BusinessEventPort;
import com.salonio.modules.business.application.port.business.out.BusinessPersistencePort;
import com.salonio.modules.business.domain.event.business.BusinessSchedulingEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@AllArgsConstructor
@Service
public class BusinessDomainService {
    private final BusinessEventPort businessEventPort;
    private final BusinessPersistencePort businessPersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(BusinessDomainService.class);


    public void findBusinessesWithScheduling(AvailabilitySchedulingEvent availabilitySchedulingEvent) {
        final List<UUID> foundUUIDs = listBusinessesByScheduling(availabilitySchedulingEvent.isScheduling());
        final BusinessSchedulingEvent businessSchedulingEvent = BusinessEventFactory
                .createBusinessSchedulingEvent(foundUUIDs);
        businessEventPort.publishBusinessSchedulingEvent(businessSchedulingEvent);
    }

    private List<UUID> listBusinessesByScheduling(boolean isScheduling) {
        List<UUID> foundIds =  businessPersistencePort.listBusinessesByScheduling(isScheduling);
        if (foundIds.isEmpty()) {
            logger.error("There are no businesses with wanted scheduling status");
            throw new RuntimeException("There are no businesses with scheduling turned to " + isScheduling);
        }
        return foundIds;
    }

}
