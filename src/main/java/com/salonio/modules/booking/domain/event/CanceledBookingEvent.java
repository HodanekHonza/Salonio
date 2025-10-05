package com.salonio.modules.booking.domain.event;

import java.util.UUID;

public record CanceledBookingEvent(UUID id) {
}
