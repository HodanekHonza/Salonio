package com.salonio.booking.internal;

import com.salonio.availability.event.AvailabilitySlotConfirmedEvent;
import com.salonio.availability.event.AvailabilitySlotNotFoundEvent;
import com.salonio.booking.enums.BookingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.event.TransactionPhase;
import org.springframework.transaction.event.TransactionalEventListener;
import java.util.UUID;

@Service
public class BookingEventListener {

    private final ApplicationEventPublisher publisher;
    private final BookingRepository bookingRepository;

    private static final Logger logger = LoggerFactory.getLogger(BookingEventListener.class);

    BookingEventListener(ApplicationEventPublisher publisher, BookingRepository bookingRepository) {
        this.publisher = publisher;
        this.bookingRepository = bookingRepository;
    }

    // TODO check if needed and if better to have transactionalEventListener, works with both now
    @EventListener
    // @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void saveBookingResult(AvailabilitySlotConfirmedEvent availabilitySlotConfirmedEvent) {
        UUID bookingId = availabilitySlotConfirmedEvent.getBookingId();

        final var pendingBooking = bookingRepository.findById(bookingId);
        pendingBooking.ifPresent(booking -> {
            booking.setStatus(BookingStatus.CONFIRMED);
            try {
                bookingRepository.save(booking);
                logger.info("Booking event CONFIRMED for booking id {}", bookingId);
            } catch (OptimisticLockingFailureException e) {
                throw new RuntimeException();
            }
        });
    }

    // TODO need to learn about transactional, eventListeners and their properties in order to make this right
    // @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    @EventListener
    void deleteBookingResult(AvailabilitySlotNotFoundEvent availabilitySlotNotFoundEvent) {
        try {
            bookingRepository.deleteById(availabilitySlotNotFoundEvent.getBookingId());
        } catch (OptimisticLockingFailureException e) {
            throw new RuntimeException();
        }
    }

}
