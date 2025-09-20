package com.salonio.modules.booking.infrastructure.persistence;

import com.salonio.modules.booking.api.dto.BookingResponse;
import com.salonio.modules.booking.api.dto.UpdateBookingRequest;
import com.salonio.modules.booking.domain.Booking;

public final class BookingMapper {

    private BookingMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    // TODO move to model
    public static void updateEntity(UpdateBookingRequest request, Booking booking) {
        booking.setStartTime(request.startTime());
        booking.setEndTime(request.endTime());
        booking.setClientId(request.clientId());
        booking.setStaffId(request.staffId());
        booking.setServiceType(request.serviceType());
        booking.setStatus(request.status());
    }


    public static BookingResponse toResponse(Booking booking) {
        if (booking == null) {
            return null;
        }
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
        Booking booking = new Booking(
                entity.getStartTime(),
                entity.getEndTime(),
                entity.getClientId(),
                entity.getStaffId(),
                entity.getServiceType(),
                entity.getStatus()
        );
        booking.setId(entity.getId());
        booking.setVersion(entity.getVersion());
        return booking;
    }

    public static BookingJpaEntity fromDomain(Booking booking) {
        BookingJpaEntity entity = new BookingJpaEntity();
        entity.setId(booking.getId());
        entity.setVersion(booking.getVersion());
        entity.setStartTime(booking.getStartTime());
        entity.setEndTime(booking.getEndTime());
        entity.setClientId(booking.getClientId());
        entity.setStaffId(booking.getStaffId());
        entity.setServiceType(booking.getServiceType());
        entity.setStatus(booking.getStatus());
        return entity;
    }

}
