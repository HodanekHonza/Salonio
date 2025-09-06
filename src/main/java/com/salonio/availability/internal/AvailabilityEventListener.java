package com.salonio.availability.internal;

import com.salonio.booking.event.SavedBookingEvent;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
public class AvailabilityEventListener {
    
    private final ApplicationEventPublisher publisher;
    private final AvailabilityRepository availabilityRepository;
    
    AvailabilityEventListener(ApplicationEventPublisher publisher, AvailabilityRepository availabilityRepository) {
        this.publisher = publisher;
        this.availabilityRepository = availabilityRepository;
    }


    @EventListener
    void checkAvailability(SavedBookingEvent event) {
        System.out.println("Checking availability");

        UUID staffId = UUID.fromString("1a2b3c4d-5e6f-7a8b-9c0d-1e2f3a4b5c6d");
        LocalDateTime from = LocalDateTime.of(2025, 9, 6, 9, 0, 0);
        LocalDateTime to = LocalDateTime.of(2025, 9, 6, 10, 0, 0);
        Pageable pageable = PageRequest.of(0, 10);

        Optional<Availability> availableSlots = availabilityRepository.findSpecificAvailableSlot(
                staffId,
                from,
                to
//                pageable
        );

        if (availableSlots.isEmpty()) {
            throw new IllegalStateException("No available available for staff " + staffId);
        }

        // Publish event with the successful message
        publisher.publishEvent(new Object());
        // Or publish with failing so it's denied in booking and it's selected as canceled or maybe even delete because it would cause spam
        System.out.println("Found " + availableSlots.get() + " slots for staff " + staffId);
    }

}
