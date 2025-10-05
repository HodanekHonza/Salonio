package com.salonio.modules.booking.infrastructure.persistence;

import com.salonio.modules.booking.api.dto.BookingResponse;
import com.salonio.modules.booking.domain.Booking;

public final class BookingMapper {

    private BookingMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static BookingResponse toResponse(Booking booking) {
        return new BookingResponse(
                booking.getId(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getClientId(),
                booking.getStaffId(),
                booking.getServiceType(),
                booking.getStatus()
        );
    }

    public static Booking toDomain(BookingJpaEntity entity) {
        return new Booking(
                entity.getId(),
                entity.getVersion(),
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getClientId(),
                entity.getStaffId(),
                entity.getServiceType(),
                entity.getStatus()
        );
    }

    public static BookingJpaEntity fromDomain(Booking booking) {
        return new BookingJpaEntity(
                booking.getId(),
                booking.getVersion(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getClientId(),
                booking.getStaffId(),
                booking.getServiceType(),
                booking.getStatus());
    }

}
