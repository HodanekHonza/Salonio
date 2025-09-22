package com.salonio.modules.availability.infrastructure.persistence;

import com.salonio.modules.availability.api.dto.AvailabilityResponse;
import com.salonio.modules.availability.api.dto.UpdateAvailabilityRequest;
import com.salonio.modules.availability.domain.Availability;
import org.springframework.stereotype.Service;

public final class AvailabilityMapper {

    AvailabilityMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    // TODO move to model
    public static Availability updateEntity(UpdateAvailabilityRequest request, Availability availability) {
        availability.setStartTime(request.startTime());
        availability.setEndTime(request.endTime());
        availability.setClientId(request.clientId());
        availability.setStaffId(request.staffId());
        return availability;
    }

    public static AvailabilityResponse toResponse(Availability availability) {
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

    public static Availability toDomain(AvailabilityJpaEntity entity) {
        Availability availability = new Availability(
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getStaffId(),
                entity.getBusinessId(),
                entity.isAvailability(),
                entity.getBookingId(),
                entity.getClientId());
        availability.setId(entity.getId());
        availability.setVersion(entity.getVersion());
        return availability;
    }

    public static AvailabilityJpaEntity fromDomain(Availability booking) {
        AvailabilityJpaEntity entity = new AvailabilityJpaEntity();
        entity.setId(booking.getId());
        entity.setVersion(booking.getVersion());
        entity.setStartTime(booking.getStartTime());
        entity.setEndTime(booking.getEndTime());
        entity.setStaffId(booking.getStaffId());
        entity.setBusinessId(booking.getBusinessId());
        entity.setAvailability(booking.isAvailability());
        entity.setBookingId(booking.getBookingId());
        entity.setClientId(booking.getClientId());
        return entity;
    }

}
