package com.salonio.modules.availability.api;

import com.salonio.modules.availability.api.dto.AvailabilityResponse;
import com.salonio.modules.availability.api.dto.CreateAvailabilityRequest;
import com.salonio.modules.availability.api.dto.UpdateAvailabilityRequest;
import java.util.UUID;

public interface AvailabilityApi {

    AvailabilityResponse createAvailability(CreateAvailabilityRequest createAvailabilityRequest);

    AvailabilityResponse getAvailability(UUID availabilityId);

    AvailabilityResponse updateAvailability(UUID availabilityId, UpdateAvailabilityRequest updateAvailabilityRequest);

    void deleteAvailability(UUID availabilityId);

}
