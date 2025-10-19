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

    public static CanceledBookingEvent createCanceledBookingEvent(Booking booking) {
        return new CanceledBookingEvent(
               booking.getStaffId(),
               booking.getStartTime(),
               booking.getEndTime()
        );
    }

    public static RescheduledBookingEvent createRescheduledBookingEvent(
            Booking booking,
            Booking oldBooking
    ) {
        return new RescheduledBookingEvent(
                booking.getStaffId(),
                oldBooking.getStartTime(),
                oldBooking.getEndTime(),
                booking.getStartTime(),
                booking.getEndTime()
        );
    }

}
