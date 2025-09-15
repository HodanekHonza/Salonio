package com.salonio.availability.application.factory;

import com.salonio.availability.api.dto.CreateAvailabilityRequest;
import com.salonio.availability.domain.Availability;

public final class AvailabilityFactory {

    private AvailabilityFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Availability create(CreateAvailabilityRequest createAvailabilityRequest) {
        return new Availability(
                createAvailabilityRequest.startTime(),
                createAvailabilityRequest.endTime(),
                createAvailabilityRequest.staffId(),
                createAvailabilityRequest.businessId(),
                createAvailabilityRequest.availability(),
                createAvailabilityRequest.bookingId(),
                createAvailabilityRequest.clientId()
        );
    }

}
