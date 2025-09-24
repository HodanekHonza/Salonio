package com.salonio.modules.booking.application.service;

import com.salonio.modules.booking.api.BookingApi;
import com.salonio.modules.booking.api.dto.BookingResponse;
import com.salonio.modules.booking.api.dto.CreateBookingRequest;
import com.salonio.modules.booking.api.dto.UpdateBookingRequest;
import com.salonio.modules.booking.application.factory.BookingFactory;
import com.salonio.modules.booking.application.port.out.BookingEventPort;
import com.salonio.modules.booking.application.port.out.BookingPersistencePort;
import com.salonio.modules.booking.domain.Booking;
import com.salonio.modules.booking.domain.enums.BookingStatus;
import com.salonio.modules.booking.exception.BookingExceptions;
import com.salonio.modules.booking.infrastructure.persistence.BookingMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.UUID;

@Service
@AllArgsConstructor
public class BookingService implements BookingApi {

    private final BookingPersistencePort bookingPersistencePort;
    private final BookingEventPort bookingEventPort;

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    @Transactional
    @Override
    public BookingResponse createBooking(CreateBookingRequest createBookingRequest, String authorizationCode) {

        final Booking newBooking = BookingFactory.createPendingBooking(createBookingRequest);
        final Booking savedBooking = saveBooking(newBooking);

        bookingEventPort.publishPendingBooking(savedBooking);
        // PENDING -> CONFIRMED Booking Status
        final Booking refreshBooking = findBooking(savedBooking.getId());

        return BookingMapper.toResponse(refreshBooking);
    }

    @Override
    public BookingResponse getBooking(UUID bookingId) {
        final Booking foundBooking = findBooking(bookingId);
        return BookingMapper.toResponse(foundBooking);
    }

    @Override
    public Page<BookingResponse> getBookingsByClientId(UUID clientId, Pageable pageable) {
       final var foundBookings = findBookingByClientId(clientId, pageable);
       return transformationToBookingsResponses(foundBookings);
    }

    @Override
    public Page<BookingResponse> getBookingsByStaffId(UUID staffId, Pageable pageable) {
        final var foundBookings = findBookingByStaffId(staffId, pageable);
        return transformationToBookingsResponses(foundBookings);
    }

    @Transactional
    @Override
    public BookingResponse updateBooking(UUID bookingId, UpdateBookingRequest request) {
        final Booking existing = findBooking(bookingId);
        final BookingStatus oldStatus = existing.getStatus();

        BookingMapper.updateEntity(request, existing);
        Booking updatedBooking = saveBooking(existing);

        bookingEventPort.publishUpdatedBooking(existing, oldStatus);
        return BookingMapper.toResponse(updatedBooking);
    }

    @Transactional
    @Override
    public void deleteBooking(UUID bookingId) {
        try {
            bookingPersistencePort.deleteById(bookingId);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Deleting booking failed");
            throw new BookingExceptions.BookingNotFoundException("Booking with id " + bookingId + " not found");
        }
        bookingEventPort.publishDeletedBooking(bookingId);
    }

    private Booking saveBooking(Booking newBooking) {
        try {
            return bookingPersistencePort.save(newBooking);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Saving booking failed");
            throw new BookingExceptions.BookingConflictException("Saving booking conflict");
        }
    }

    private Booking findBooking(UUID bookingId) {
        return bookingPersistencePort.findById(bookingId)
                .orElseThrow(() -> new BookingExceptions.BookingNotFoundException("Booking with id " + bookingId + " not found"));
    }

    private Page<Booking> findBookingByClientId(UUID clientId, Pageable pageable) {
       return bookingPersistencePort.findByClientId(clientId, pageable);
    }

    private Page<Booking> findBookingByStaffId(UUID staffId, Pageable pageable) {
        return bookingPersistencePort.findByStaffId(staffId, pageable);
    }

    private Page<BookingResponse> transformationToBookingsResponses(Page<Booking> bookings) {
        return bookings.map(BookingMapper::toResponse);
    }

}
