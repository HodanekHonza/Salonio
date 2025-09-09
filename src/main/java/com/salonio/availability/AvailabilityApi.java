package com.salonio.availability;

import com.salonio.availability.dto.AvailabilityResponse;
import com.salonio.availability.dto.CreateAvailabilityRequest;
import com.salonio.availability.dto.UpdateAvailabilityRequest;
import jakarta.validation.Valid;

import java.util.UUID;

public interface AvailabilityApi {

    // hmm will this work?
    AvailabilityResponse createAvailability(@Valid CreateAvailabilityRequest createAvailabilityRequest);

    AvailabilityResponse getAvailability(UUID availabilityId);

    AvailabilityResponse updateAvailability(UpdateAvailabilityRequest updateAvailabilityRequest);

    void deleteAvailability(UUID availabilityId);

}
