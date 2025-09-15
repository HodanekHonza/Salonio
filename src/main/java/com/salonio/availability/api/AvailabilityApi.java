package com.salonio.availability.api;

import com.salonio.availability.api.dto.AvailabilityResponse;
import com.salonio.availability.api.dto.CreateAvailabilityRequest;
import com.salonio.availability.api.dto.UpdateAvailabilityRequest;
import java.util.UUID;

public interface AvailabilityApi {

    AvailabilityResponse createAvailability(CreateAvailabilityRequest createAvailabilityRequest);

    AvailabilityResponse getAvailability(UUID availabilityId);

    AvailabilityResponse updateAvailability(UUID availabilityId, UpdateAvailabilityRequest updateAvailabilityRequest);

    void deleteAvailability(UUID availabilityId);

}
