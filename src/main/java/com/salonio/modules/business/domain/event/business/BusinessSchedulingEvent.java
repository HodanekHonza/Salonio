package com.salonio.modules.business.domain.event.business;

import java.util.List;
import java.util.UUID;

public record BusinessSchedulingEvent(
        List<UUID> businessesWithScheduling
) {
}
