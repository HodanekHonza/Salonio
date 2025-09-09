package com.salonio.availability.internal;

import com.salonio.availability.dto.AvailabilityResponse;
import com.salonio.availability.dto.UpdateAvailabilityRequest;
import org.springframework.stereotype.Service;

@Service
public class AvailabilityMapper {

    void updateEntity(UpdateAvailabilityRequest request, Availability availability) {
        availability.setStartTime(request.startTime());
        availability.setEndTime(request.endTime());
        availability.setClientId(request.clientId());
        availability.setStaffId(request.staffId());
    }

    AvailabilityResponse toResponse(Availability availability) {
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
