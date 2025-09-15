package com.salonio.availability.infrastructure.messaging;

import com.salonio.availability.application.port.out.AvailabilityEventPort;
import com.salonio.availability.application.port.out.AvailabilityPersistencePort;
import com.salonio.availability.domain.Availability;
import com.salonio.availability.domain.event.AvailabilitySlotConfirmedEvent;
import com.salonio.availability.exception.AvailabilityExceptions;
import com.salonio.booking.domain.event.PendingBookingEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.event.EventListener;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

@Service
@AllArgsConstructor
class AvailabilityEventListener {

    private final AvailabilityEventPort availabilityEventPort;
    private final AvailabilityPersistencePort availabilityPersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityEventListener.class);

    @EventListener
    @Transactional
    void checkAvailability(PendingBookingEvent pendingBookingEvent) {

        UUID bookingId = pendingBookingEvent.getBookingId();
        UUID staffId = pendingBookingEvent.getStaffId();
        UUID clientId = pendingBookingEvent.getClientId();
        LocalDateTime startTime = pendingBookingEvent.getStartTime();
        LocalDateTime endTime = pendingBookingEvent.getEndTime();

        logger.info("Trying to find specific available slot with staffId: {}, startTime: {}, endTime: {}.",
                staffId.toString(), startTime.toString(), endTime.toString());
        Optional<Availability> availableSlot = availabilityPersistencePort.findSpecificAvailableSlot(
                staffId,
                startTime,
                endTime
        );

        Availability slot = availableSlot.orElseThrow(() -> {
            logger.error("No available slot found for staffId: {}, startTime: {}, endTime: {}.",
                    staffId, startTime, endTime);
            return new AvailabilityExceptions.AvailabilityNotFoundException("No available slot for staff " + staffId);
        });

        AvailabilitySlotConfirmedEvent availabilitySlotConfirmedEvent;

        try {
            logger.info("Starting updating process");
//            availabilitySlotConfirmedEvent = slot.confirm(bookingId, clientId);
            availabilitySlotConfirmedEvent = retryMechanism(slot, bookingId, clientId);
            logger.info("Successfully updated slot with ID: {}, To Status: {}, BookingId: {}, ClientId: {}.",
                    slot.getId(), slot.isAvailability(),
                    slot.getBookingId(), slot.getClientId());
        } catch (OptimisticLockingFailureException e) {
            logger.error("Updating availability with ID: {} failed.", slot.getId());
            throw new AvailabilityExceptions.AvailabilityConflictException(
                    "Updating availability with ID:" + slot.getId() + "failed.");
        }
        availabilityEventPort.publishAvailabilitySlotConfirmedEvent(availabilitySlotConfirmedEvent);
    }

    private AvailabilitySlotConfirmedEvent retryMechanism(Availability slot, UUID bookingId, UUID clientId) {
        for (int i = 0; true; i++) {
            try {
                return slot.confirm(bookingId, clientId);
            } catch (OptimisticLockingFailureException e) {
                if (i == 2) throw e; // give up
                logger.warn("Retrying due to optimistic lock failure...");
            }
        }
    }

}
