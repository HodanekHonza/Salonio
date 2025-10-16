package com.salonio.modules.availability.application.service;

import com.salonio.modules.availability.application.factory.AvailabilityEventFactory;
import com.salonio.modules.availability.application.port.out.AvailabilityEventPort;
import com.salonio.modules.availability.application.port.out.AvailabilityPersistencePort;
import com.salonio.modules.availability.domain.Availability;
import com.salonio.modules.availability.domain.event.AvailabilitySlotCanceledEvent;
import com.salonio.modules.availability.domain.event.AvailabilitySlotConfirmedEvent;
import com.salonio.modules.availability.exception.AvailabilityExceptions;
import com.salonio.modules.booking.domain.event.CanceledBookingEvent;
import com.salonio.modules.booking.domain.event.PendingBookingEvent;
import com.salonio.modules.business.domain.event.business.BusinessSchedulingEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import java.time.LocalDateTime;
import java.util.UUID;

@AllArgsConstructor
@Service
public class AvailabilityDomainService {
    private final AvailabilityEventPort availabilityEventPort;
    private final AvailabilityPersistencePort availabilityPersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityDomainService.class);

    public void checkAvailability(PendingBookingEvent pendingBookingEvent) {
        final Availability slot = getAvailableSlot(pendingBookingEvent);

        confirmAvailability(
                slot,
                pendingBookingEvent.bookingId(),
                pendingBookingEvent.clientId()
        );

        final AvailabilitySlotConfirmedEvent confirmedEvent = AvailabilityEventFactory
                .createAvailabilitySlotConfirmedEvent(slot.getBookingId());

        availabilityEventPort.publishAvailabilitySlotConfirmedEvent(confirmedEvent);
    }

    public void cancelAppointment(CanceledBookingEvent canceledBookingEvent) {
        final Availability slot = findBookedSlot(canceledBookingEvent);

        freeAvailability(
                slot
        );

        final AvailabilitySlotCanceledEvent canceledEvent = AvailabilityEventFactory
                .createAvailabilitySlotCanceledEvent(slot.getBookingId());

        availabilityEventPort.publishAvailabilitySlotCanceledEvent(canceledEvent);
    }

    public void scheduleAvailability(BusinessSchedulingEvent businessSchedulingEvent) {
        // 1. get current weeks availabilities for business IDs
        // 2. create new availabilities for next week based on the current
        // 3. send Notifications
    }

    private Availability getAvailableSlot(PendingBookingEvent pendingBookingEvent) {
        final UUID staffId = pendingBookingEvent.staffId();
        final LocalDateTime startTime = pendingBookingEvent.startTime();
        final LocalDateTime endTime = pendingBookingEvent.endTime();

        return availabilityPersistencePort.findSpecificAvailableSlot(
                staffId,
                startTime,
                endTime
        ).orElseThrow(() -> {
            // TODO move log to handler
            logger.error("No available slot found for staffId: {}, startTime: {}, endTime: {}.",
                    staffId, startTime, endTime);
            return new AvailabilityExceptions.
                    AvailabilityNotFoundException("No available slot for staff " + staffId);
        });

    }

    private void confirmAvailability(
            Availability slot, UUID bookingId, UUID clientId) {
        try {
            logger.info("Starting updating process");
            final Availability confirmedAvailability = slot.confirm(bookingId, clientId);
            saveAvailability(confirmedAvailability);
        } catch (OptimisticLockingFailureException e) {
            // TODO move log to handler
            logger.error("Updating availability with ID: {} failed.", slot.getId());
            throw new AvailabilityExceptions.AvailabilityConflictException(
                    "Updating availability with ID:" + slot.getId() + "failed.");
        }
    }

    private void freeAvailability(Availability slot) {
        try {
            logger.info("Starting canceling process");
            final Availability canceledAvailability = slot.cancel();
            saveAvailability(canceledAvailability);
        } catch (OptimisticLockingFailureException e) {
            // TODO move log to handler
            logger.error("Canceling availability with ID: {} failed.", slot.getId());
            throw new AvailabilityExceptions.AvailabilityConflictException(
                    "Canceling availability with ID:" + slot.getId() + "failed.");
        }
    }

    private Availability findBookedSlot(CanceledBookingEvent canceledBookingEvent) {
        final UUID staffId = canceledBookingEvent.staffId();
        final LocalDateTime startTime = canceledBookingEvent.startTime();
        final LocalDateTime endTime = canceledBookingEvent.endTime();


        return availabilityPersistencePort.findSpecificSlot(
                canceledBookingEvent.staffId(),
                canceledBookingEvent.startTime(),
                canceledBookingEvent.endTime()
        ).orElseThrow(() -> {
            // TODO move log to handler
            logger.error("No slot found for staffId: {}, startTime: {}, endTime: {}.",
                    staffId, startTime, endTime);
            return new AvailabilityExceptions.
                    AvailabilityNotFoundException("No slot for staff " + staffId);
        });
    }



    private void saveAvailability(Availability confirmedAvailability) {
        availabilityPersistencePort.save(confirmedAvailability);
        logger.info("Successfully updated slot with ID: {}, To Status: {}, BookingId: {}, ClientId: {}.",
                confirmedAvailability.getId(), confirmedAvailability.isAvailability(),
                confirmedAvailability.getBookingId(), confirmedAvailability.getClientId());
    }

}
