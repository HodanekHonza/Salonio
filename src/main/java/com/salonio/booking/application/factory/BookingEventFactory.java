package com.salonio.booking.application.factory;

import com.salonio.booking.domain.Booking;
import com.salonio.booking.domain.event.DeletedBookingEvent;
import com.salonio.booking.domain.event.PendingBookingEvent;

import java.util.UUID;

public final class BookingEventFactory {

    private BookingEventFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static PendingBookingEvent createPendingBookingEvent(Booking booking) {
        return new PendingBookingEvent(
                booking.getId(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getStaffId(),
                booking.getClientId()
        );
    }

    public static DeletedBookingEvent createDeletedBookingEvent(UUID bookingId) {
        return new DeletedBookingEvent(
                bookingId,
                bookingId
        );
    }

}
