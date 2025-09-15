package com.salonio.availability.infrastructure.persistence;

import com.salonio.availability.api.dto.AvailabilityResponse;
import com.salonio.availability.api.dto.UpdateAvailabilityRequest;
import com.salonio.availability.domain.Availability;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityMapper {

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
}
