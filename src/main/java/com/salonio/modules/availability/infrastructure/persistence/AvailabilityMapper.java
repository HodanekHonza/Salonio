package com.salonio.modules.availability.infrastructure.persistence;

import com.salonio.modules.availability.api.dto.AvailabilityResponse;
import com.salonio.modules.availability.domain.Availability;

public final class AvailabilityMapper {

    AvailabilityMapper() {
        throw new UnsupportedOperationException("Utility class");
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
        return new Availability(
                entity.getId(),
                entity.getVersion(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getStaffId(),
                entity.getBusinessId(),
                entity.isAvailability(),
                entity.getBookingId(),
                entity.getClientId()
        );
    }

    public static AvailabilityJpaEntity fromDomain(Availability booking) {
        return new AvailabilityJpaEntity(
                booking.getId(),
                booking.getVersion(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getStaffId(),
                booking.getBusinessId(),
                booking.isAvailability(),
                booking.getBookingId(),
                booking.getClientId()

        );
    }

}
