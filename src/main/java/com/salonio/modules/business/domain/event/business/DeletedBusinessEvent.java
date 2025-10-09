package com.salonio.modules.business.domain.event.business;

import java.util.UUID;

public record DeletedBusinessEvent(
        UUID bookingId,
        UUID userId
) {
}
