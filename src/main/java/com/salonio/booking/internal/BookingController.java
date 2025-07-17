package com.salonio.booking.internal;

import com.salonio.booking.dto.CreateBookingRequest;
import com.salonio.booking.dto.GetBookingRequest;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/Booking")
class BookingController {

    private final BookingService bookingService;

    BookingController(BookingService queueService) {
        this.bookingService = queueService;
    }

    @GetMapping("/getBooking")
    List<Booking> getBooking(@RequestBody GetBookingRequest getBookingRequest) {
        return bookingService.getBooking(getBookingRequest.id());
    }

    @PostMapping("/createBooking")
    Booking createBooking(@RequestBody CreateBookingRequest name) {
        return bookingService.createBooking(name);
    }
}
