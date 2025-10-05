package com.salonio.modules.booking.infrastructure.controller;

import com.salonio.modules.booking.api.BookingApi;
import com.salonio.modules.booking.api.dto.BookingResponse;
import com.salonio.modules.booking.api.dto.CreateBookingRequest;
import com.salonio.modules.booking.api.dto.UpdateBookingRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/booking")
class BookingController {

    private final BookingApi bookingApi;

    @PostMapping()
    BookingResponse createBooking(@Valid @RequestBody CreateBookingRequest createBookingRequest,
                                  @RequestHeader("Authorization") String authorizationCode) {
        return bookingApi.createBooking(createBookingRequest, authorizationCode);
    }

    @GetMapping("/{bookingId}")
    BookingResponse getBooking(@PathVariable UUID bookingId) {
        return bookingApi.getBooking(bookingId);
    }

    @GetMapping("{bookingId}/client/{clientId}")
    Page<BookingResponse> getBookingByClientId(@PathVariable UUID bookingId,
                                               @PathVariable UUID clientId,
                                               Pageable pageable) {
        return bookingApi.getBookingsByClientId(clientId, pageable);
    }

    @GetMapping("{bookingId}/staff/{staffId}")
    Page<BookingResponse> getBookingStaffId(@PathVariable UUID bookingId,
                                            @PathVariable UUID staffId,
                                            Pageable pageable) {
        return bookingApi.getBookingsByStaffId(staffId, pageable);
    }

    @PutMapping("/{bookingId}")
    BookingResponse updateBooking(@PathVariable UUID bookingId,
                                  @Valid @RequestBody UpdateBookingRequest updateBookingRequest) {
        return bookingApi.updateBooking(bookingId, updateBookingRequest);
    }

    @DeleteMapping("/{bookingId}")
    ResponseEntity<Void> deleteBooking(@PathVariable UUID bookingId) {
        bookingApi.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

}
