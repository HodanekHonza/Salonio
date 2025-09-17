package com.salonio.modules.booking.application.factory;

import com.salonio.modules.booking.domain.Booking;
import com.salonio.modules.booking.domain.event.CanceledBookingEvent;
import com.salonio.modules.booking.domain.event.DeletedBookingEvent;
import com.salonio.modules.booking.domain.event.PendingBookingEvent;
import com.salonio.modules.booking.domain.event.RescheduledBookingEvent;

import java.util.UUID;

public final class BookingEventFactory {

    private BookingEventFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    // TODO rewrite into record
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

    public static CanceledBookingEvent createCanceledBookingEvent(UUID clientId) {
        return new CanceledBookingEvent(
                clientId
        );
    }

    public static RescheduledBookingEvent createRescheduledBookingEvent(UUID clientId) {
        return new RescheduledBookingEvent(
                clientId
        );
    }

}
