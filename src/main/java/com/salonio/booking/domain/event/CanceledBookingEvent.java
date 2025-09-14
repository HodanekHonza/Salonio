package com.salonio.booking.domain.event;

import com.salonio.booking.domain.enums.BookingStatus;

import java.util.UUID;

public record CanceledBookingEvent(UUID id, BookingStatus bookingStatus) {
}
