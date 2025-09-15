package com.salonio.booking.infrastructure.messaging;

import com.salonio.availability.domain.event.AvailabilitySlotConfirmedEvent;
import com.salonio.availability.domain.event.AvailabilitySlotNotFoundEvent;
import com.salonio.booking.application.port.out.BookingPersistencePort;
import com.salonio.booking.domain.Booking;
import com.salonio.booking.exception.BookingExceptions;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class BookingEventListener {

    private final ApplicationEventPublisher publisher; // TODO use for notifications
    private final BookingPersistencePort bookingPort;

    private static final Logger logger = LoggerFactory.getLogger(BookingEventListener.class);


    @Transactional
    @EventListener
    public void saveBookingResult(AvailabilitySlotConfirmedEvent event) {
        UUID bookingId = event.getBookingId();

        Booking pendingBooking = bookingPort.findById(bookingId)
                .orElseThrow(() -> new BookingExceptions.BookingNotFoundException(
                        "No booking found with id " + bookingId));

        try {
            pendingBooking.confirm();
            logger.info("Booking {} confirmed successfully.", bookingId);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Optimistic locking conflict while confirming booking {}", bookingId);
            throw new BookingExceptions.BookingConflictException("Booking conflict for id: " + bookingId, e);
        }
    }

    @Transactional
    @EventListener
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
