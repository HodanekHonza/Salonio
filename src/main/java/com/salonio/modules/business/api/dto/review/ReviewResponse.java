package com.salonio.modules.business.api.dto.review;

import java.util.UUID;

public record ReviewResponse(
        UUID id,
        String text,
        UUID clientId,
        UUID businessId,
        Integer rating
) {
}
