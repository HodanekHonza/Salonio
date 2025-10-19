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
import com.salonio.modules.booking.domain.event.RescheduledBookingEvent;
import com.salonio.modules.business.domain.event.business.BusinessSchedulingEvent;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
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
        final Availability slot = findBookedSlotFromCanceledBookingEvent(canceledBookingEvent);

        freeAvailability(
                slot
        );

        final AvailabilitySlotCanceledEvent canceledEvent = AvailabilityEventFactory
                .createAvailabilitySlotCanceledEvent(slot.getBookingId());

        availabilityEventPort.publishAvailabilitySlotCanceledEvent(canceledEvent);
    }

    public void rescheduleAppointment(RescheduledBookingEvent rescheduledBookingEvent) {
        final Availability slot = findBookedSlotFromRescheduledBookingEvent(rescheduledBookingEvent);
        final var slotBookingId = slot.getBookingId();
        final var slotClientId = slot.getClientId();

        freeAvailability(
                slot
        );

        setNewStartAndEndTime(slot, rescheduledBookingEvent);

        final Availability newAvailableSlot = getAvailableSlot(slot);

        confirmAvailability(
                newAvailableSlot,
                slotBookingId,
                slotClientId
        );
        // TODO sendout event to notification Module
    }

    public void scheduleAvailability(BusinessSchedulingEvent businessSchedulingEvent) {
        final List<UUID> businessIds = businessSchedulingEvent.businessesWithScheduling();
        final LocalDate today = LocalDate.now();
        final LocalDate monday = today.minusDays(6);

        final List<Availability> availabilities = businessIds.stream().flatMap(
                        value -> availabilityPersistencePort.
                                findAvailabilityByBusinessIdAndStartEndDate(
                                        value,
                                        today,
                                        monday,
                                        Pageable.unpaged()
                                )
                                .getContent().stream()
                )
                .toList();

        availabilities.forEach(value -> {
            var end = value.getEndTime().plusDays(7);
            var start = value.getStartTime().plusDays(7);
            value.setEndTime(end);
            value.setStartTime(start);
        });

        availabilityPersistencePort.saveAll(availabilities);
        System.out.println(availabilities);
        // TODO sendout event to notification Module
    }

    private Availability getAvailableSlot(PendingBookingEvent pendingBookingEvent) {
        final UUID staffId = pendingBookingEvent.staffId();
        final LocalDateTime startTime = pendingBookingEvent.startTime();
        final LocalDateTime endTime = pendingBookingEvent.endTime();

        return findAvailableSlot(staffId, startTime, endTime);
    }

    private Availability getAvailableSlot(Availability availability) {
        final UUID staffId = availability.getStaffId();
        final LocalDateTime startTime = availability.getStartTime();
        final LocalDateTime endTime = availability.getEndTime();

        return findAvailableSlot(staffId, startTime, endTime);
    }

    private void confirmAvailability(
            Availability slot, UUID bookingId, UUID clientId) {
        try {
            logger.info("Starting updating process");
            final Availability confirmedAvailability = slot.confirm(bookingId, clientId);
            saveAvailability(confirmedAvailability);
        } catch (OptimisticLockingFailureException e) {
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
            logger.error("Canceling availability with ID: {} failed.", slot.getId());
            throw new AvailabilityExceptions.AvailabilityConflictException(
                    "Canceling availability with ID:" + slot.getId() + "failed.");
        }
    }

    private Availability findBookedSlotFromCanceledBookingEvent(CanceledBookingEvent canceledBookingEvent) {
        final UUID staffId = canceledBookingEvent.staffId();
        final LocalDateTime startTime = canceledBookingEvent.startTime();
        final LocalDateTime endTime = canceledBookingEvent.endTime();
        return findSpecificSlot(staffId, startTime, endTime);
    }


    private Availability findBookedSlotFromRescheduledBookingEvent(RescheduledBookingEvent canceledBookingEvent) {
        final UUID staffId = canceledBookingEvent.staffId();
        final LocalDateTime startTime = canceledBookingEvent.originalStartTime();
        final LocalDateTime endTime = canceledBookingEvent.originalEndTime();
        return findSpecificSlot(staffId, startTime, endTime);
    }

    private void saveAvailability(Availability confirmedAvailability) {
        availabilityPersistencePort.save(confirmedAvailability);
        logger.info("Successfully updated slot with ID: {}, To Status: {}, BookingId: {}, ClientId: {}.",
                confirmedAvailability.getId(), confirmedAvailability.isAvailability(),
                confirmedAvailability.getBookingId(), confirmedAvailability.getClientId());
    }


    private Availability findAvailableSlot(UUID staffId, LocalDateTime startTime, LocalDateTime endTime) {
        return availabilityPersistencePort.findSpecificAvailableSlot(
                staffId,
                startTime,
                endTime
        ).orElseThrow(() -> {
            logger.error("No available slot found for staffId: {}, originalStartTime: {}, originalEndTime: {}.",
                    staffId, startTime, endTime);
            return new AvailabilityExceptions.
                    AvailabilityNotFoundException("No available slot for staff " + staffId);
        });
    }

    private Availability findSpecificSlot(UUID staffId, LocalDateTime startTime, LocalDateTime endTime) {
        return availabilityPersistencePort.findSpecificSlot(
                staffId,
                startTime,
                endTime
        ).orElseThrow(() -> {
            logger.error("No slot found for staffId: {}, originalStartTime: {}, originalEndTime: {}.",
                    staffId, startTime, endTime);
            return new AvailabilityExceptions.
                    AvailabilityNotFoundException("No slot for staff " + staffId);
        });
    }

    private void setNewStartAndEndTime(Availability slot,
                                       RescheduledBookingEvent rescheduledBookingEvent) {
        slot.setStartTime(rescheduledBookingEvent.newStartTime());
        slot.setEndTime(rescheduledBookingEvent.newEndTime());
    }

}
