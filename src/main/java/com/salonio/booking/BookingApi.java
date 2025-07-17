package com.salonio.booking;

import com.salonio.booking.dto.CreateBookingRequest;
import com.salonio.booking.internal.Booking;

import java.util.List;
import java.util.UUID;

public interface BookingApi {

    Booking createBooking(CreateBookingRequest createBookingRequest);

    List<Booking> getBooking(UUID id);

}
