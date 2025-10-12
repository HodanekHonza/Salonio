package com.salonio.modules.booking.application.service;

import com.salonio.modules.booking.domain.Booking;
import com.salonio.modules.booking.domain.enums.BookingStatus;
import com.salonio.modules.common.event.DomainEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.util.UUID;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingEventServiceTest {

    @Mock
    private DomainEventPublisher publisher;

    @InjectMocks
    private BookingEventService bookingEventService;

    private Booking booking;
    private UUID bookingId;

    @BeforeEach
    void setUp() {
        bookingId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        booking = new Booking();
        booking.setId(bookingId);
        booking.setClientId(clientId);
        booking.setStatus(BookingStatus.PENDING);
    }

    @Test
    void testPublishPendingBooking() {
        bookingEventService.publishPendingBooking(booking);

        // Verify publisher is called
        verify(publisher).publish(any());
    }

    @Test
    void testPublishDeletedBooking() {
        bookingEventService.publishDeletedBooking(bookingId);

        // Verify publisher is called
        verify(publisher).publish(any());
    }

    @Test
    void testPublishUpdatedBooking_StatusChanged_Canceled() {
        booking.setStatus(BookingStatus.CANCELLED);
        BookingStatus oldStatus = BookingStatus.PENDING;

        bookingEventService.publishUpdatedBooking(booking, oldStatus);

        verify(publisher).publish(any());
    }

    @Test
    void testPublishUpdatedBooking_StatusChanged_Rescheduled() {
        booking.setStatus(BookingStatus.RESCHEDULED);
        BookingStatus oldStatus = BookingStatus.PENDING;

        bookingEventService.publishUpdatedBooking(booking, oldStatus);

        verify(publisher).publish(any());
    }

    @Test
    void testPublishUpdatedBooking_StatusUnchanged() {
        BookingStatus oldStatus = booking.getStatus(); // same as current

        bookingEventService.publishUpdatedBooking(booking, oldStatus);

        // No event should be published since status didn't change
        verify(publisher, never()).publish(any());
    }

    @Test
    void testPublishUpdatedBooking_StatusChanged_NoHandler() {
        booking.setStatus(BookingStatus.PENDING); // PENDING has no handler
        BookingStatus oldStatus = BookingStatus.CANCELLED;

        bookingEventService.publishUpdatedBooking(booking, oldStatus);

        verify(publisher, never()).publish(any());
    }

}
