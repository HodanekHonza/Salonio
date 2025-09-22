package com.salonio.modules.booking.application.service;

import com.salonio.modules.common.event.DomainEventPublisher;
import com.salonio.modules.booking.application.factory.BookingEventFactory;
import com.salonio.modules.booking.application.port.out.BookingEventPort;
import com.salonio.modules.booking.domain.Booking;
import com.salonio.modules.booking.domain.enums.BookingStatus;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Map;
import java.util.UUID;
import java.util.function.Consumer;

@Service
public class BookingEventService implements BookingEventPort {

    private final DomainEventPublisher publisher;
    private final Map<BookingStatus, Consumer<Booking>> statusEventHandlers;
    private static final Logger logger = LoggerFactory.getLogger(BookingEventService.class);

    public BookingEventService(DomainEventPublisher publisher) {
        this.publisher = publisher;
        // Using a simple strategy-like approach with a map instead of if/else
        // Overkill here (only 2 statuses), but useful to remember for future cases
        this.statusEventHandlers = Map.of(
                BookingStatus.CANCELED, booking -> {
                    var event = BookingEventFactory.createCanceledBookingEvent(booking.getClientId());
                    logger.info("Published CanceledBookingEvent for booking {}", booking.getId());
                    publisher.publish(event);
                },
                BookingStatus.RESCHEDULED, booking -> {
                    var event = BookingEventFactory.createRescheduledBookingEvent(booking.getClientId());
                    logger.info("Published RescheduledBookingEvent for booking {}", booking.getId());
                    publisher.publish(event);
                }
        );
    }

    @Override
    public void publishPendingBooking(Booking booking) {
        final var event = BookingEventFactory.createPendingBookingEvent(booking);
        logger.info("Published PendingBookingEvent for booking {}", booking.getId());
        publisher.publish(event);
    }

    @Override
    public void publishDeletedBooking(UUID bookingId) {
        final var event = BookingEventFactory.createDeletedBookingEvent(bookingId);
        logger.info("Published DeleteBookingEvent for booking {}", bookingId);
        publisher.publish(event);
    }

    @Override
    public void publishUpdatedBooking(Booking booking, BookingStatus oldStatus) {
        Consumer<Booking> handler = statusEventHandlers.get(booking.getStatus());
        if (handler != null && booking.getStatus() != oldStatus) {
            handler.accept(booking);
        }
    }

}
