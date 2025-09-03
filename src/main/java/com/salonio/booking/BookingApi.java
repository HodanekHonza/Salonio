package com.salonio.booking;

import com.salonio.booking.dto.BookingResponse;
import com.salonio.booking.dto.CreateBookingRequest;
import com.salonio.booking.dto.UpdateBookingRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.time.LocalDateTime;
import java.util.UUID;

public interface BookingApi {

    BookingResponse createBooking(CreateBookingRequest createBookingRequest);

    BookingResponse getBooking(UUID id);

    BookingResponse updateBooking(UUID id, UpdateBookingRequest updateBookingRequest);

//    Page<BookingResponse> getBookingByClientIdAndDateTime(UUID clientId, LocalDateTime dateTime, Pageable pageable);

}
