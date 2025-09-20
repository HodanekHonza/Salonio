package com.salonio.modules.booking.application.factory;

import com.salonio.modules.booking.api.dto.CreateBookingRequest;
import com.salonio.modules.booking.domain.Booking;
import com.salonio.modules.booking.domain.enums.BookingStatus;

public final class BookingFactory {

    private BookingFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Booking createPendingBooking(CreateBookingRequest request) {
        return new Booking(
                request.startTime(),
                request.endTime(),
                request.clientId(),
                request.staffId(),
                request.serviceType(),
                BookingStatus.PENDING
        );
    }

}
