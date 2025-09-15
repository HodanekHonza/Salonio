package com.salonio.booking.application.port.out;

import com.salonio.booking.domain.Booking;
import com.salonio.booking.domain.enums.BookingStatus;
import java.util.UUID;

public interface BookingEventPort {
    void publishPendingBooking(Booking booking);
    void publishDeletedBooking(UUID bookingId);
    void publishUpdatedBooking(UUID bookingId, BookingStatus status);
}
