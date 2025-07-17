package com.salonio.booking.internal;

import com.salonio.booking.BookingApi;
import com.salonio.booking.dto.CreateBookingRequest;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;
import java.util.List;
import java.util.UUID;

@Service
class BookingService implements BookingApi {

    private final ApplicationEventPublisher publisher;
    private final BookingRepository bookingRepository;

    BookingService(ApplicationEventPublisher publisher, BookingRepository bookingRepository) {
        this.publisher = publisher;
        this.bookingRepository = bookingRepository;
    }

    @Override
    public List<Booking> getBooking(UUID name) {
        final var foundQueue = bookingRepository.findByServiceType("Haircut");
        publisher.publishEvent(foundQueue);
        return foundQueue;
    }

    @Override
    public Booking createBooking(CreateBookingRequest createBookingRequest) {

        final var newBooking = new Booking(createBookingRequest.startTime(), createBookingRequest.duration(),
                createBookingRequest.clientId(), createBookingRequest.staffId(),
                createBookingRequest.serviceType(), createBookingRequest.bookingStatus());
        // add try catch
        final var savedBooking = bookingRepository.save(newBooking);
        publisher.publishEvent(savedBooking); // publish event instead of booking
        return savedBooking;
    }
}
