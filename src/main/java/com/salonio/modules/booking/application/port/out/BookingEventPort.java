package com.salonio.modules.booking.application.port.out;

import com.salonio.modules.booking.domain.Booking;
import com.salonio.modules.booking.domain.enums.BookingStatus;
import java.util.UUID;

public interface BookingEventPort {

    void publishPendingBooking(Booking booking);

    void publishDeletedBooking(UUID bookingId);

    void publishUpdatedBooking(Booking booking, Booking oldBooking);

}
