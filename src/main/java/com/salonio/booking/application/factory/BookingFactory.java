package com.salonio.booking.application.factory;

import com.salonio.booking.api.dto.CreateBookingRequest;
import com.salonio.booking.domain.Booking;
import com.salonio.booking.domain.enums.BookingStatus;

public final class BookingFactory {

    private BookingFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Booking create(CreateBookingRequest request) {
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
