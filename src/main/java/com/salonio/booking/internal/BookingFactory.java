package com.salonio.booking.internal;

import com.salonio.booking.dto.CreateBookingRequest;
import com.salonio.booking.enums.BookingStatus;
import org.springframework.stereotype.Service;

//@Service
final class BookingFactory {

    private BookingFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    static Booking create(CreateBookingRequest request) {
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
