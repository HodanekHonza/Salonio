package com.salonio.availability.internal;

import com.salonio.availability.event.AvailabilitySlotConfirmedEvent;
import com.salonio.availability.event.AvailabilitySlotNotFoundEvent;
import com.salonio.booking.event.PendingBookingEvent;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import java.time.LocalDateTime;
import java.util.Optional;
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

    // TODO check if needed and if better to have transactionalEventListener, works with both now
    //@EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void checkAvailability(PendingBookingEvent pendingBookingEvent) {
        logger.info("Start/Event Listener - Checking availability");

        UUID staffId = pendingBookingEvent.getStaffId();
        LocalDateTime startTime = pendingBookingEvent.getStartTime();
        LocalDateTime endTime = pendingBookingEvent.getEndTime();

        logger.info("Trying to find specific available slot with ID: {}, startTime: {}, endTime: {}.",
                staffId.toString(), startTime.toString(), endTime.toString());
        Optional<Availability> availableSlots = availabilityRepository.findSpecificAvailableSlot(
                staffId,
                startTime,
                endTime
        );

        if (availableSlots.isEmpty()) {
            logger.error("No available available for slot {}", staffId);
            publisher.publishEvent(new AvailabilitySlotNotFoundEvent(pendingBookingEvent.getBookingId()));
            throw new IllegalStateException("No available available for staff " + staffId);
        }

        final var slot = availableSlots.get();

        try {
            logger.info("Starting updating process");

            slot.setAvailability(false);
            availabilityRepository.save(slot);

            logger.info("Successfully updated slot with ID: {}, To Status: {}.",
                    slot.getId(), slot.isAvailability());
            publisher.publishEvent(new AvailabilitySlotConfirmedEvent(pendingBookingEvent.getBookingId()));
        } catch (OptimisticLockingFailureException e) {
            logger.error("Updating availability with ID: {} failed.", slot.getId());
            throw new OptimisticLockingFailureException("");
        }

    }

}
