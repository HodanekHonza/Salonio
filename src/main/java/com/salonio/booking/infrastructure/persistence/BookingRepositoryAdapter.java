package com.salonio.booking.infrastructure.persistence;

import com.salonio.booking.application.port.BookingPersistencePort;
import com.salonio.booking.domain.Booking;
import org.springframework.stereotype.Component;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class BookingRepositoryAdapter implements BookingPersistencePort {

    private final BookingRepository bookingRepository;

    public BookingRepositoryAdapter(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking save(Booking booking) {
        return bookingRepository.save(booking);
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> findByClientId(UUID clientId) {
        return bookingRepository.findByClientId(clientId);
    }

    @Override
    public List<Booking> findByStartTimeAndStaffId(LocalDateTime startTime, UUID staffId) {
        return bookingRepository.findByStartTimeAndStaffId(startTime, staffId);
    }
}
