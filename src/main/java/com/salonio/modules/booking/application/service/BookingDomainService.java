package com.salonio.modules.booking.application.service;

import com.salonio.libs.common.util.RetryUtils;
import com.salonio.modules.availability.domain.event.AvailabilitySlotConfirmedEvent;
import com.salonio.modules.availability.domain.event.AvailabilitySlotNotFoundEvent;
import com.salonio.modules.booking.application.port.out.BookingPersistencePort;
import com.salonio.modules.booking.domain.Booking;
import com.salonio.modules.booking.exception.BookingExceptions;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class BookingDomainService {

    private final ApplicationEventPublisher publisher; // TODO Move responsibility to event service for notification service
    private final BookingPersistencePort bookingPort;

    private static final Logger logger = LoggerFactory.getLogger(BookingDomainService.class);

    @Transactional
    public void saveBookingResult(AvailabilitySlotConfirmedEvent event) {
        UUID bookingId = event.getBookingId();

        Booking pendingBooking = bookingPort.findById(bookingId)
                .orElseThrow(() -> new BookingExceptions.BookingNotFoundException(
                        "No booking found with id " + bookingId));

        try {
            RetryUtils.retryMechanism(pendingBooking);
            logger.info("Booking {} confirmed successfully.", bookingId);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Optimistic locking conflict while confirming booking {}", bookingId);
            throw new BookingExceptions.BookingConflictException("Booking conflict for id: " + bookingId, e);
        }
    }

    @Transactional
    public void deleteBookingResult(AvailabilitySlotNotFoundEvent event) {
        UUID bookingId = event.getBookingId();

        try {
            bookingPort.deleteById(bookingId);
            logger.info("Booking {} deleted due to unavailability.", bookingId);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Optimistic locking conflict while deleting booking {}", bookingId);
            throw new BookingExceptions.BookingConflictException("Booking conflict for id: " + bookingId, e);
        }
    }

}
