package com.salonio.modules.booking.infrastructure.persistence;

import com.salonio.modules.booking.application.port.out.BookingPersistencePort;
import com.salonio.modules.booking.domain.Booking;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Component
public class BookingRepositoryAdapter implements BookingPersistencePort {

    private final BookingRepository bookingRepository;

    private static final Logger logger = LoggerFactory.getLogger(BookingRepositoryAdapter.class);

    public BookingRepositoryAdapter(BookingRepository bookingRepository) {
        this.bookingRepository = bookingRepository;
    }

    @Override
    public Booking save(Booking booking) {
        logger.debug("Saving booking: {}", booking);
        Booking saved = bookingRepository.save(booking);
        logger.debug("Booking saved with id: {}", saved.getId());
        return saved;
    }

    @Override
    public void deleteById(UUID bookingId) {
        logger.debug("Deleting booking with id: {}", bookingId);
        bookingRepository.deleteById(bookingId);
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        logger.debug("Finding booking with id: {}", id);
        return bookingRepository.findById(id);
    }

    @Override
    public List<Booking> findByClientId(UUID clientId) {
        logger.debug("Finding bookings for clientId: {}", clientId);
        return bookingRepository.findByClientId(clientId);
    }

}
