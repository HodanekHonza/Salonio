package com.salonio.modules.availability.infrastructure.persistence;

import com.salonio.modules.availability.api.dto.AvailabilityResponse;
import com.salonio.modules.availability.api.dto.UpdateAvailabilityRequest;
import com.salonio.modules.availability.domain.Availability;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityMapper {

    // TODO move to model
    public Availability updateEntity(UpdateAvailabilityRequest request, Availability availability) {
        availability.setStartTime(request.startTime());
        availability.setEndTime(request.endTime());
        availability.setClientId(request.clientId());
        availability.setStaffId(request.staffId());
        return availability;
    }

    public AvailabilityResponse toResponse(Availability availability) {
        if (availability == null) {
            return null;
        }
        return new AvailabilityResponse(
                availability.getId(),
                availability.getStartTime(),
                availability.getEndTime(),
                availability.getStaffId(),
                availability.getBusinessId(),
                availability.isAvailability(),
                availability.getBookingId(),
                availability.getClientId()
        );
    }

    // TODO Entity ↔ Domain

}
