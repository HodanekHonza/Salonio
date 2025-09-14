package com.salonio.booking.domain.event;

import com.salonio.booking.domain.enums.BookingStatus;

import java.util.UUID;

public record UpdatedBookingEvent(UUID id, BookingStatus bookingStatus) {
}
