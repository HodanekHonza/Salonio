package com.salonio.modules.booking.application.port.in;

import com.salonio.modules.booking.api.dto.CreateBookingRequest;
import com.salonio.modules.booking.domain.Booking;

// TODO
public interface BookingCreateUseCasePort {

    Booking createBooking(CreateBookingRequest createBookingRequest);

}
