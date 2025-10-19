package com.salonio.modules.booking.application.service;

import com.salonio.modules.booking.domain.event.CanceledBookingEvent;
import com.salonio.modules.booking.domain.event.DeletedBookingEvent;
import com.salonio.modules.booking.domain.event.PendingBookingEvent;
import com.salonio.modules.booking.domain.event.RescheduledBookingEvent;
import com.salonio.modules.common.event.DomainEventPublisher;
import com.salonio.modules.booking.application.factory.BookingEventFactory;
import com.salonio.modules.booking.application.port.out.BookingEventPort;
import com.salonio.modules.booking.domain.Booking;
import com.salonio.modules.booking.domain.enums.BookingStatus;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookingEventService implements BookingEventPort {

    private final DomainEventPublisher publisher;
    private static final Logger logger = LoggerFactory.getLogger(BookingEventService.class);

    @Override
    public void publishPendingBooking(Booking booking) {
        final PendingBookingEvent event = BookingEventFactory.createPendingBookingEvent(booking);
        logger.info("Published PendingBookingEvent for booking {}", booking.getId());
        publisher.publish(event);
    }

    @Override
    public void publishDeletedBooking(UUID bookingId) {
        final DeletedBookingEvent event = BookingEventFactory.createDeletedBookingEvent(bookingId);
        logger.info("Published DeleteBookingEvent for booking {}", bookingId);
        publisher.publish(event);
    }

    @Override
    public void publishUpdatedBooking(Booking booking, Booking oldBooking) {
        final BookingStatus oldStatus = oldBooking.getStatus();
        if (booking.getStatus() != oldBooking.getStatus()) {
            switch (booking.getStatus()) {
                case CANCELLED -> publishCancelledBooking(booking, oldStatus);
                case RESCHEDULED -> publishRescheduledBooking(booking, oldStatus, oldBooking);
            }
        }
    }

    private void publishCancelledBooking(Booking booking, BookingStatus oldStatus) {
        if (booking.getStatus() != oldStatus) {
            final CanceledBookingEvent event = BookingEventFactory.createCanceledBookingEvent(booking);
            logger.info("Published CanceledBookingEvent for booking {}", booking.getId());
            publisher.publish(event);
        }
    }

    private void publishRescheduledBooking(Booking booking, BookingStatus oldStatus, Booking oldBooking) {
        if (booking.getStatus() != oldStatus) {
            final RescheduledBookingEvent event = BookingEventFactory.createRescheduledBookingEvent(booking, oldBooking);
            logger.info("Published RescheduledBookingEvent for booking {}", booking.getId());
            publisher.publish(event);
        }
    }

}
