package com.salonio.modules.availability.api;

import com.salonio.modules.availability.api.dto.AvailabilityResponse;
import com.salonio.modules.availability.api.dto.CreateAvailabilityRequest;
import com.salonio.modules.availability.api.dto.UpdateAvailabilityRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDate;
import java.util.UUID;

public interface AvailabilityApi {

    AvailabilityResponse createAvailability(CreateAvailabilityRequest createAvailabilityRequest);

    AvailabilityResponse getAvailability(UUID availabilityId);

    Page<AvailabilityResponse> listAvailabilityByDateAndBusinessId(UUID businessId, LocalDate date, Pageable pageable);

    AvailabilityResponse updateAvailability(UUID availabilityId, UpdateAvailabilityRequest updateAvailabilityRequest);

    void deleteAvailability(UUID availabilityId);

}
