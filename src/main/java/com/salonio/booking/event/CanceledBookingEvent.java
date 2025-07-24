package com.salonio.booking.event;

import com.salonio.booking.enums.BookingStatus;

import java.util.UUID;

public record CanceledBookingEvent(UUID id, BookingStatus bookingStatus) {
}
