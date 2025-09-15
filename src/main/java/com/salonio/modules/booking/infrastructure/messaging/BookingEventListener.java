package com.salonio.modules.booking.infrastructure.messaging;

import com.salonio.modules.availability.domain.event.AvailabilitySlotConfirmedEvent;
import com.salonio.modules.availability.domain.event.AvailabilitySlotNotFoundEvent;
import com.salonio.modules.booking.application.service.BookingDomainService;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;


@AllArgsConstructor
@Service
public class BookingEventListener {

    private final BookingDomainService bookingDomainService;


    @EventListener
    public void saveBookingResult(AvailabilitySlotConfirmedEvent event) {
        bookingDomainService.saveBookingResult(event);
    }

    @EventListener
    public void deleteBookingResult(AvailabilitySlotNotFoundEvent event) {
        bookingDomainService.deleteBookingResult(event);
    }

}
