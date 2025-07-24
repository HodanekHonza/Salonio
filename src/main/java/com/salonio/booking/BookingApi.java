package com.salonio.booking;

import com.salonio.booking.dto.BookingResponse;
import com.salonio.booking.dto.CreateBookingRequest;
import com.salonio.booking.dto.UpdateBookingRequest;

import java.time.LocalDateTime;
import java.util.List;
import java.util.UUID;

public interface BookingApi {

    BookingResponse createBooking(CreateBookingRequest createBookingRequest);

    BookingResponse getBooking(UUID id);

    BookingResponse updateBooking(UUID id, UpdateBookingRequest updateBookingRequest);

    List<BookingResponse> getBookingByClientIdAndDateTime(UUID clientId, LocalDateTime dateTime);

}
