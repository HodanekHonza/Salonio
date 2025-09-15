package com.salonio.booking.application.service;

import com.salonio.booking.api.BookingApi;
import com.salonio.booking.api.dto.BookingResponse;
import com.salonio.booking.api.dto.CreateBookingRequest;
import com.salonio.booking.api.dto.UpdateBookingRequest;
import com.salonio.booking.application.factory.BookingFactory;
import com.salonio.booking.application.port.out.BookingEventPort;
import com.salonio.booking.application.port.out.BookingPersistencePort;
import com.salonio.booking.domain.Booking;
import com.salonio.booking.domain.enums.BookingStatus;
import com.salonio.booking.exception.BookingExceptions;
import com.salonio.booking.infrastructure.persistence.BookingMapper;
import lombok.AllArgsConstructor;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
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

        final Booking newBooking = BookingFactory.create(createBookingRequest);
        final Booking savedBooking = saveBooking(newBooking);

        bookingEventPort.publishPendingBooking(newBooking);

        return BookingMapper.toResponse(savedBooking);
    }


    @Transactional(readOnly = true) // not needed
    @Override
    public BookingResponse getBooking(UUID bookingId) {
        final Booking foundBooking = findBooking(bookingId);
        return BookingMapper.toResponse(foundBooking);
    }

    @Transactional
    @Override
    public BookingResponse updateBooking(UUID bookingId, UpdateBookingRequest request) {
        final Booking existing = findBooking(bookingId);

        BookingMapper.updateEntity(request, existing);
        Booking updatedBooking = saveBooking(existing);

        final BookingResponse updatedBookingResponse = BookingMapper.toResponse(updatedBooking);
        // TODO move to entity
        final BookingStatus oldStatus = existing.getStatus();
//        if (request.status() == BookingStatus.CANCELED && oldStatus != BookingStatus.CANCELED) {
//            CanceledBookingEvent canceledBookingEvent = new CanceledBookingEvent(request.clientId(), BookingStatus.CANCELED);
//            publisher.publishEvent(canceledBookingEvent);
//        } else if (request.status() == BookingStatus.RESCHEDULED && oldStatus != BookingStatus.RESCHEDULED) {
//            RescheduledBookingEvent rescheduledBookingEvent = new RescheduledBookingEvent();
//            publisher.publishEvent(rescheduledBookingEvent);
//        }
//        // else if (request.status() == BookingStatus.CONFIRMED && oldStatus != BookingStatus.CONFIRMED) { ... }
//        final var updatedBookingEvent = new UpdatedBookingEvent(updatedBookingResponse.id(), BookingStatus.RESCHEDULED);
//        publisher.publishEvent(updatedBookingEvent);
        return updatedBookingResponse;
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

}
