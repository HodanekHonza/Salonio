package com.salonio.modules.booking.api;

import com.salonio.modules.booking.api.dto.BookingResponse;
import com.salonio.modules.booking.api.dto.CreateBookingRequest;
import com.salonio.modules.booking.api.dto.UpdateBookingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.UUID;

public interface BookingApi {

    BookingResponse createBooking(CreateBookingRequest createBookingRequest, String authorizationCode);

    BookingResponse getBooking(UUID id);

    Page<BookingResponse> getBookingsByClientId(UUID clientId, Pageable pageable);

    Page<BookingResponse> getBookingsByStaffId(UUID staffId, Pageable pageable);

    BookingResponse updateBooking(UUID id, UpdateBookingRequest updateBookingRequest);

    void deleteBooking(UUID id);

}
