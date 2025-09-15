package com.salonio.modules.booking.api;

import com.salonio.modules.booking.api.dto.BookingResponse;
import com.salonio.modules.booking.api.dto.CreateBookingRequest;
import com.salonio.modules.booking.api.dto.UpdateBookingRequest;

import java.util.UUID;

public interface BookingApi {

    BookingResponse createBooking(CreateBookingRequest createBookingRequest, String authorizationCode);

    BookingResponse getBooking(UUID id);

    BookingResponse updateBooking(UUID id, UpdateBookingRequest updateBookingRequest);

    void deleteBooking(UUID id);

}
