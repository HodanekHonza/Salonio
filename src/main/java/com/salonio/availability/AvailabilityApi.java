package com.salonio.availability;

import com.salonio.availability.dto.AvailabilityResponse;

import java.util.UUID;

public interface AvailabilityApi {

    AvailabilityResponse getAvailability(UUID availabilityId);

    AvailabilityResponse createAvailability();

    AvailabilityResponse updateAvailability();
    void deleteAvailability();
}
