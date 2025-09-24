package com.salonio.modules.booking.infrastructure.persistence;

import com.salonio.modules.booking.application.port.out.BookingPersistencePort;
import com.salonio.modules.booking.domain.Booking;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Component
public class BookingRepositoryAdapter implements BookingPersistencePort {

    private final BookingRepository bookingRepository;

    private static final Logger logger = LoggerFactory.getLogger(BookingRepositoryAdapter.class);

    @Override
    public Booking save(Booking booking) {
        logger.debug("Saving booking: {}", booking);
        final BookingJpaEntity bookingJpaEntity = BookingMapper.fromDomain(booking);
        final BookingJpaEntity saved = bookingRepository.save(bookingJpaEntity);
        logger.debug("Booking saved with id: {}", saved.getId());
        return BookingMapper.toDomain(saved);
    }

    @Override
    public Optional<Booking> findById(UUID id) {
        logger.debug("Finding booking with id: {}", id);
        return bookingRepository.findById(id)
                .map(BookingMapper::toDomain);
    }

    @Override
    public void deleteById(UUID bookingId) {
        logger.debug("Deleting booking with id: {}", bookingId);
        bookingRepository.deleteById(bookingId);
    }


    @Override
    public Page<Booking> findByClientId(UUID clientId, Pageable pageable) {
        logger.debug("Finding bookings for clientId: {}", clientId);
        return bookingRepository.findByClientId(clientId, pageable)
                .map(BookingMapper::toDomain);
    }

    @Override
    public Page<Booking> findByStaffId(UUID staffId, Pageable pageable) {
        logger.debug("Finding bookings for staffId: {}", staffId);
        return bookingRepository.findByStaffId(staffId, pageable)
                .map(BookingMapper::toDomain);
    }

}
