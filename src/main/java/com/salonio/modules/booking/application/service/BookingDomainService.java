package com.salonio.modules.booking.application.service;

import com.salonio.modules.common.event.DomainEventPublisher;
import com.salonio.modules.availability.domain.event.AvailabilitySlotConfirmedEvent;
import com.salonio.modules.availability.domain.event.AvailabilitySlotNotFoundEvent;
import com.salonio.modules.booking.application.port.out.BookingPersistencePort;
import com.salonio.modules.booking.domain.Booking;
import com.salonio.modules.booking.exception.BookingExceptions;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class BookingDomainService {

    private final DomainEventPublisher publisher;
    private final BookingPersistencePort bookingPort;

    private static final Logger logger = LoggerFactory.getLogger(BookingDomainService.class);

    @Transactional
    public void saveBookingResult(final AvailabilitySlotConfirmedEvent event) {
        final Booking pendingBooking = findBooking(event.getBookingId());
        final Booking confirmedBooking = confirmBooking(pendingBooking);
        bookingPort.save(confirmedBooking);
    }

    @Transactional
    public void deleteBookingResult(final AvailabilitySlotNotFoundEvent event) {
        final UUID bookingId = event.getBookingId();

        try {
            bookingPort.deleteById(bookingId);
            logger.info("Booking {} deleted due to unavailability.", bookingId);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Optimistic locking conflict while deleting booking {}", bookingId);
            throw new BookingExceptions.BookingConflictException("Booking conflict for id: " + bookingId, e);
        }
    }

    private Booking findBooking(final UUID bookingId) {
        return bookingPort.findById(bookingId)
                .orElseThrow(() -> new BookingExceptions.BookingNotFoundException(
                        "No booking found with id " + bookingId));
    }

    private Booking confirmBooking(final Booking pendingBooking) {
        UUID bookingId = pendingBooking.getId();
        try {
            pendingBooking.confirm();
            logger.info("Booking {} confirmed successfully.", bookingId);
            return pendingBooking;
        } catch (OptimisticLockingFailureException e) {
            logger.error("Optimistic locking conflict while confirming booking {}", bookingId);
            throw new BookingExceptions.BookingConflictException("Booking conflict for id: " + bookingId, e);
        }
    }

}
