package com.salonio.booking.internal;

import com.salonio.booking.dto.BookingResponse;
import com.salonio.booking.dto.UpdateBookingRequest;

final class BookingMapper {

    private BookingMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    static void updateEntity(UpdateBookingRequest request, Booking booking) {
        booking.setStartTime(request.startTime());
        booking.setEndTime(request.endTime());
        booking.setClientId(request.clientId());
        booking.setStaffId(request.staffId());
        booking.setServiceType(request.serviceType());
        booking.setStatus(request.status());
    }

    static BookingResponse toResponse(Booking booking) {
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
}
