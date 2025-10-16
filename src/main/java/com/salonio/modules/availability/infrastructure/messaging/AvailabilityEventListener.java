package com.salonio.modules.availability.infrastructure.messaging;

import com.salonio.modules.availability.application.service.AvailabilityDomainService;
import com.salonio.modules.booking.domain.event.CanceledBookingEvent;
import com.salonio.modules.booking.domain.event.PendingBookingEvent;
import com.salonio.modules.business.domain.event.business.BusinessSchedulingEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@AllArgsConstructor
class AvailabilityEventListener {

    private final AvailabilityDomainService domainService;

    @EventListener
    @Transactional
    void checkAvailability(PendingBookingEvent pendingBookingEvent) {
        domainService.checkAvailability(pendingBookingEvent);
    }

    @EventListener
    @Transactional
    void cancelAppointment(CanceledBookingEvent canceledBookingEvent) {
        domainService.cancelAppointment(canceledBookingEvent);
    }

    @EventListener
    void scheduleAvailability(BusinessSchedulingEvent businessSchedulingEvent) {
        domainService.scheduleAvailability(businessSchedulingEvent);
    }

}
