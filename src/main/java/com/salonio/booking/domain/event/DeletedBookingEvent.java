package com.salonio.booking.domain.event;

import java.util.UUID;

public record DeletedBookingEvent(UUID bookingId, UUID userId) {
}
