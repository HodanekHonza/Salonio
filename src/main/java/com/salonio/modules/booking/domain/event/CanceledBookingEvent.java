package com.salonio.modules.booking.domain.event;

import com.salonio.modules.booking.domain.enums.BookingStatus;

import java.util.UUID;

public record CanceledBookingEvent(UUID id) {
}
