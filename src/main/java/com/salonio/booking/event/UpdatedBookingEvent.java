package com.salonio.booking.event;

import com.salonio.booking.enums.BookingStatus;

import java.util.UUID;

public record UpdatedBookingEvent(UUID id, BookingStatus bookingStatus) {
}
