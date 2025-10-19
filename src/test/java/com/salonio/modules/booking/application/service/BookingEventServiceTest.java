package com.salonio.modules.booking.application.service;

import com.salonio.modules.booking.domain.Booking;
import com.salonio.modules.booking.domain.enums.BookingStatus;
import com.salonio.modules.booking.domain.event.*;
import com.salonio.modules.common.event.DomainEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;

import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingEventServiceTest {

    @Mock
    private DomainEventPublisher publisher;

    @InjectMocks
    private BookingEventService bookingEventService;

    private Booking booking;
    private Booking oldBooking;
    private UUID bookingId;

    @BeforeEach
    void setUp() {
        bookingId = UUID.randomUUID();

        booking = new Booking();
        booking.setId(bookingId);
        booking.setStatus(BookingStatus.PENDING);

        oldBooking = new Booking();
        oldBooking.setId(bookingId);
        oldBooking.setStatus(BookingStatus.PENDING);
    }

    @Test
    void shouldPublishPendingBookingEvent() {
        bookingEventService.publishPendingBooking(booking);
        verify(publisher).publish(any(PendingBookingEvent.class));
    }

    @Test
    void shouldPublishDeletedBookingEvent() {
        bookingEventService.publishDeletedBooking(bookingId);
        verify(publisher).publish(any(DeletedBookingEvent.class));
    }

    @Test
    void shouldPublishCanceledBookingEvent_WhenStatusChangedToCanceled() {
        oldBooking.setStatus(BookingStatus.PENDING);
        booking.setStatus(BookingStatus.CANCELLED);

        bookingEventService.publishUpdatedBooking(booking, oldBooking);

        verify(publisher).publish(any(CanceledBookingEvent.class));
    }

    @Test
    void shouldPublishRescheduledBookingEvent_WhenStatusChangedToRescheduled() {
        oldBooking.setStatus(BookingStatus.PENDING);
        booking.setStatus(BookingStatus.RESCHEDULED);

        bookingEventService.publishUpdatedBooking(booking, oldBooking);

        verify(publisher).publish(any(RescheduledBookingEvent.class));
    }

    @Test
    void shouldNotPublishEvent_WhenStatusDidNotChange() {
        booking.setStatus(BookingStatus.PENDING);
        oldBooking.setStatus(BookingStatus.PENDING);

        bookingEventService.publishUpdatedBooking(booking, oldBooking);

        verify(publisher, never()).publish(any());
    }

    @Test
    void shouldNotPublishEvent_WhenNewStatusHasNoHandler() {
        oldBooking.setStatus(BookingStatus.CANCELLED);
        booking.setStatus(BookingStatus.PENDING); // No handler in switch

        bookingEventService.publishUpdatedBooking(booking, oldBooking);

        verify(publisher, never()).publish(any());
    }
}
