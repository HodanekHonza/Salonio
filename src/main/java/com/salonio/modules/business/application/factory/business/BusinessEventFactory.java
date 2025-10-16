package com.salonio.modules.business.application.factory.business;

import com.salonio.modules.business.domain.event.business.BusinessSchedulingEvent;
import java.util.List;
import java.util.UUID;

public final class BusinessEventFactory {

    private BusinessEventFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static BusinessSchedulingEvent createBusinessSchedulingEvent(List<UUID> uuidList) {
        return new BusinessSchedulingEvent(
                uuidList
        );
    }

}
