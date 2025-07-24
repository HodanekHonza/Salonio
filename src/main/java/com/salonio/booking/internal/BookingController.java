package com.salonio.booking.internal;

import com.salonio.booking.dto.BookingResponse;
import com.salonio.booking.dto.CreateBookingRequest;
import com.salonio.booking.dto.GetBookingStaffRequest;
import com.salonio.booking.dto.UpdateBookingRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/booking")
class BookingController {

    private final BookingService bookingService;

    BookingController(BookingService queueService) {
        this.bookingService = queueService;
    }

    @PostMapping()
    BookingResponse createBooking(@RequestBody CreateBookingRequest createBookingRequest) {
        return bookingService.createBooking(createBookingRequest);
    }

    @GetMapping("/{bookingId}")
    BookingResponse getBooking(@PathVariable UUID bookingId) {
        return bookingService.getBooking(bookingId);
    }

    @PutMapping("/{bookingId}")
    BookingResponse updateBooking(@PathVariable UUID bookingId, @RequestBody UpdateBookingRequest updateBookingRequest) {
        return bookingService.updateBooking(bookingId, updateBookingRequest);
    }

    @DeleteMapping("/{bookingId}")
    ResponseEntity<Void> deleteBooking(@PathVariable UUID bookingId) {
        bookingService.deleteBooking(bookingId);
        return ResponseEntity.noContent().build();
    }

    @GetMapping("/staff")
    List<BookingResponse> getBookingByStaffId(@RequestBody GetBookingStaffRequest getBookingRequest) {
        return bookingService.getBookingByClientIdAndDateTime(
                getBookingRequest.staffId(),
                getBookingRequest.startTime()
        );
    }

}
