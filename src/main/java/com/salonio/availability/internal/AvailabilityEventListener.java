package com.salonio.availability.internal;

import com.salonio.availability.event.AvailabilitySlotConfirmedEvent;
import com.salonio.booking.domain.event.PendingBookingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.UUID;

@Service
class AvailabilityEventListener {

    private final ApplicationEventPublisher publisher;
    private final AvailabilityRepository availabilityRepository;

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityEventListener.class);

    AvailabilityEventListener(ApplicationEventPublisher publisher, AvailabilityRepository availabilityRepository) {
        this.publisher = publisher;
        this.availabilityRepository = availabilityRepository;
    }

    @EventListener
    @Transactional
        // if its transactional and JPA managed entity it doesn't need explicit .save()
    void checkAvailability(PendingBookingEvent pendingBookingEvent) {

        UUID bookingId = pendingBookingEvent.getBookingId();
        UUID staffId = pendingBookingEvent.getStaffId();
        UUID clientId = pendingBookingEvent.getClientId();
        LocalDateTime startTime = pendingBookingEvent.getStartTime();
        LocalDateTime endTime = pendingBookingEvent.getEndTime();

        logger.info("Trying to find specific available slot with staffId: {}, startTime: {}, endTime: {}.",
                staffId.toString(), startTime.toString(), endTime.toString());
        final Availability availableSlot = availabilityRepository.findSpecificAvailableSlot(
                staffId,
                startTime,
                endTime
        );

        if (availableSlot == null) {
            logger.error("No available slot found for staffId: {}, startTime: {}, endTime: {}.",
                    staffId, startTime, endTime);
            throw new IllegalStateException("No available slot for staff " + staffId); // TODO Custom Exceptions
        }

        AvailabilitySlotConfirmedEvent availabilitySlotConfirmedEvent;

        try {
            logger.info("Starting updating process");
            availabilitySlotConfirmedEvent = availableSlot.confirm(bookingId, clientId);
            logger.info("Successfully updated slot with ID: {}, To Status: {}, BookingId: {}, ClientId: {}.",
                    availableSlot.getId(), availableSlot.isAvailability(),
                    availableSlot.getBookingId(), availableSlot.getClientId());
        } catch (OptimisticLockingFailureException e) {
            logger.error("Updating availability with ID: {} failed.", availableSlot.getId());
            throw new OptimisticLockingFailureException("Updating availability with ID:" + availableSlot.getId() + "failed.");
        }
        publisher.publishEvent(availabilitySlotConfirmedEvent);
    }

}
