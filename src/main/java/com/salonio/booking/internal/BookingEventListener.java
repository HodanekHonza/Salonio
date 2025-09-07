package com.salonio.booking.internal;

import com.salonio.availability.event.AvailabilitySlotConfirmedEvent;
import com.salonio.availability.event.AvailabilitySlotNotFoundEvent;
import com.salonio.booking.enums.BookingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
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
    //@EventListener
    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void saveBookingResult(AvailabilitySlotConfirmedEvent availabilitySlotConfirmedEvent) {
        Booking savedBooking;
        logger.info("BOOKING EVENT LISTENER HERE");
        UUID bookingId = availabilitySlotConfirmedEvent.getBookingId();

        var pendingBooking = bookingRepository.findById(bookingId);
        pendingBooking.get().setStatus(BookingStatus.CONFIRMED);
        try {
            bookingRepository.save(pendingBooking.get());
        } catch (OptimisticLockingFailureException e) {
            throw new RuntimeException();
        }

    }

    @TransactionalEventListener(phase = TransactionPhase.AFTER_COMMIT)
    void deleteBookingResult(AvailabilitySlotNotFoundEvent availabilitySlotNotFoundEvent) {
        try {
            bookingRepository.deleteById(availabilitySlotNotFoundEvent.getBookingId());
        } catch (OptimisticLockingFailureException e) {
            throw new RuntimeException();
        }
    }

}
