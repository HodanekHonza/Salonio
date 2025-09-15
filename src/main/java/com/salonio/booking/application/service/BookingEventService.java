package com.salonio.booking.application.service;

import com.salonio.booking.application.factory.BookingEventFactory;
import com.salonio.booking.application.port.out.BookingEventPort;
import com.salonio.booking.domain.Booking;
import com.salonio.booking.domain.enums.BookingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
public class BookingEventService implements BookingEventPort {

    private final ApplicationEventPublisher publisher;
    private static final Logger logger = LoggerFactory.getLogger(BookingEventService.class);

    public BookingEventService(ApplicationEventPublisher publisher) {
        this.publisher = publisher;
    }

    @Override
    public void publishPendingBooking(Booking booking) {
        final var event = BookingEventFactory.createPendingBookingEvent(booking);
        logger.info("Published PendingBookingEvent for booking {}", booking.getId());
        publisher.publishEvent(event);
    }

    @Override
    public void publishDeletedBooking(UUID bookingId) {
        final var event = BookingEventFactory.createDeletedBookingEvent(bookingId);
        logger.info("Published DeleteBookingEvent for booking {}", bookingId);
        publisher.publishEvent(event);
    }

    @Override
    public void publishUpdatedBooking(UUID bookingId, BookingStatus status) {

    }
}
