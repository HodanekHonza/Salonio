package com.salonio.modules.business.application.service.business;

import com.salonio.modules.availability.domain.event.AvailabilitySchedulingEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
public class BusinessEventListener {

    private final BusinessDomainService domainService;

    @EventListener
    @Transactional
    void checkAvailability(AvailabilitySchedulingEvent availabilitySchedulingEvent) {
        domainService.findBusinessesWithScheduling(availabilitySchedulingEvent);
    }

}
