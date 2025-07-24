package com.salonio.booking.event;

import java.util.UUID;

public record DeletedBookingEvent(UUID bookingId, UUID userId) {
}
