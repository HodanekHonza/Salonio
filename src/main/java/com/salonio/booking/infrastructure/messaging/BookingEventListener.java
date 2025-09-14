package com.salonio.booking.infrastructure.messaging;

import com.salonio.availability.event.AvailabilitySlotConfirmedEvent;
import com.salonio.availability.event.AvailabilitySlotNotFoundEvent;
import com.salonio.booking.application.port.BookingPersistencePort;
import com.salonio.booking.domain.enums.BookingStatus;
import com.salonio.booking.infrastructure.persistence.BookingRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.context.event.EventListener;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.UUID;

@Service
public class BookingEventListener {

    private final ApplicationEventPublisher publisher;
    private final BookingRepository bookingRepository;
    private final BookingPersistencePort bookingPort;

    private static final Logger logger = LoggerFactory.getLogger(BookingEventListener.class);

    BookingEventListener(ApplicationEventPublisher publisher, BookingRepository bookingRepository,  BookingPersistencePort bookingPort) {
        this.publisher = publisher;
        this.bookingRepository = bookingRepository;
        this.bookingPort = bookingPort;
    }

    @Transactional
    @EventListener
    void saveBookingResult(AvailabilitySlotConfirmedEvent availabilitySlotConfirmedEvent) {
        UUID bookingId = availabilitySlotConfirmedEvent.getBookingId();

        final var pendingBooking = bookingPort.findById(bookingId);
        pendingBooking.ifPresent(booking -> {
            try {
                booking.setStatus(BookingStatus.CONFIRMED);
                logger.info("Booking event CONFIRMED for booking id {}", bookingId);
            } catch (OptimisticLockingFailureException e) {
                throw new RuntimeException();
            }
        });
    }

    @Transactional
    @EventListener
    void deleteBookingResult(AvailabilitySlotNotFoundEvent availabilitySlotNotFoundEvent) {
        try {
            bookingRepository.deleteById(availabilitySlotNotFoundEvent.getBookingId());
        } catch (OptimisticLockingFailureException e) {
            throw new RuntimeException();
        }
    }

}
