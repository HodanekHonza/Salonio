package com.salonio.booking.internal;

import com.salonio.booking.dto.BookingResponse;
import com.salonio.booking.dto.CreateBookingRequest;
import com.salonio.booking.dto.UpdateBookingRequest;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.time.LocalDateTime;
import java.util.UUID;

@RestController
@RequestMapping("/booking")
class BookingController {

    private final BookingService bookingService;

    BookingController(BookingService queueService) {
        this.bookingService = queueService;
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
                                  @RequestBody UpdateBookingRequest updateBookingRequest) {
        return bookingService.updateBooking(bookingId, updateBookingRequest);
    }

    @DeleteMapping("/{bookingId}")
    ResponseEntity<Void> deleteBooking(@PathVariable UUID bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

}
