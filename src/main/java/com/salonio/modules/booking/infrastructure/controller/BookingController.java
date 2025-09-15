package com.salonio.modules.booking.infrastructure.controller;

import com.salonio.modules.booking.api.dto.BookingResponse;
import com.salonio.modules.booking.api.dto.CreateBookingRequest;
import com.salonio.modules.booking.api.dto.UpdateBookingRequest;
import com.salonio.modules.booking.application.service.BookingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.UUID;

@RestController
@RequestMapping("/booking")
class BookingController {

    private final BookingService bookingService;

    BookingController(BookingService bookingService) {
        this.bookingService = bookingService;
    }

    @PostMapping()
    BookingResponse createBooking(@Valid @RequestBody CreateBookingRequest createBookingRequest, @RequestHeader("Authorization") String authorizationCode) {
        return bookingService.createBooking(createBookingRequest, authorizationCode);
    }

    @GetMapping("/{bookingId}")
    BookingResponse getBooking(@PathVariable UUID bookingId) {
        return bookingService.getBooking(bookingId);
    }

    @PutMapping("/{bookingId}")
    BookingResponse updateBooking(@PathVariable UUID bookingId,
                                  @Valid @RequestBody UpdateBookingRequest updateBookingRequest) {
        return bookingService.updateBooking(bookingId, updateBookingRequest);
    }

    @DeleteMapping("/{bookingId}")
    ResponseEntity<Void> deleteBooking(@PathVariable UUID bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

}
