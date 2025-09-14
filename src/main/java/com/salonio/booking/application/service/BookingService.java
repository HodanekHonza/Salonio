package com.salonio.booking.application.service;

import com.salonio.booking.api.BookingApi;
import com.salonio.booking.api.dto.BookingResponse;
import com.salonio.booking.api.dto.CreateBookingRequest;
import com.salonio.booking.api.dto.UpdateBookingRequest;
import com.salonio.booking.application.factory.BookingFactory;
import com.salonio.booking.domain.Booking;
import com.salonio.booking.domain.enums.BookingStatus;
import com.salonio.booking.domain.event.*;
import com.salonio.booking.exception.BookingExceptions;
import com.salonio.booking.infrastructure.persistence.BookingMapper;
import com.salonio.booking.infrastructure.persistence.BookingRepository;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

@Service
public class BookingService implements BookingApi {

    private final ApplicationEventPublisher publisher;
    private final BookingRepository bookingRepository;

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    BookingService(ApplicationEventPublisher publisher, BookingRepository bookingRepository) {
        this.publisher = publisher;
        this.bookingRepository = bookingRepository;
    }

    @Transactional
    @Override
    public BookingResponse createBooking(CreateBookingRequest createBookingRequest, String authorizationCode) {

        final Booking newBooking = BookingFactory.create(createBookingRequest);
        final Booking savedBooking;

        try {
            logger.info("Creating a new booking with the following request: {}", createBookingRequest);
            savedBooking = bookingRepository.save(newBooking);
            logger.info("Booking saved successfully id: {}", savedBooking.getId());
        } catch (OptimisticLockingFailureException e) {
            logger.error("Creating booking failed");
            throw new BookingExceptions.BookingConflictException("Creating booking conflict");
        }
        publishPendingBookingEvent(newBooking);

        logger.info("Booking event published -> mappingToBookingResponses");
        return BookingMapper.toResponse(savedBooking);
    }

    @Transactional(readOnly = true) // not needed
    @Override
    public BookingResponse getBooking(UUID bookingId) {
        final Booking foundBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingExceptions.BookingNotFoundException("Booking with id " + bookingId + " not found"));
        return BookingMapper.toResponse(foundBooking);
    }

    @Transactional
    @Override
    public BookingResponse updateBooking(UUID bookingId, UpdateBookingRequest request) {
        final Booking existing = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new BookingExceptions.BookingNotFoundException("Booking with id " + bookingId + " not found"));

        BookingMapper.updateEntity(request, existing);

        Booking updatedBooking;
        try {
            logger.info("Updating booking with the following request: {}", request);
            updatedBooking = bookingRepository.save(existing);
            logger.info("Booking updated successfully id: {}", updatedBooking.getId());
        } catch (OptimisticLockingFailureException e) {
            throw new BookingExceptions.BookingConflictException("Booking with id " + bookingId + " was modified concurrently. Please retry.", e);
        }

        final var updatedBookingResponse = BookingMapper.toResponse(updatedBooking);
        final BookingStatus oldStatus = existing.getStatus();
        if (request.status() == BookingStatus.CANCELED && oldStatus != BookingStatus.CANCELED) {
            CanceledBookingEvent canceledBookingEvent = new CanceledBookingEvent(request.clientId(), BookingStatus.CANCELED);
            publisher.publishEvent(canceledBookingEvent);
        } else if (request.status() == BookingStatus.RESCHEDULED && oldStatus != BookingStatus.RESCHEDULED) {
            RescheduledBookingEvent rescheduledBookingEvent = new RescheduledBookingEvent();
            publisher.publishEvent(rescheduledBookingEvent);
        }
        // else if (request.status() == BookingStatus.CONFIRMED && oldStatus != BookingStatus.CONFIRMED) { ... }
        final var updatedBookingEvent = new UpdatedBookingEvent(updatedBookingResponse.id(), BookingStatus.RESCHEDULED);
        publisher.publishEvent(updatedBookingEvent);

        return updatedBookingResponse;
    }

    @Transactional
    @Override
    public void deleteBooking(UUID bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new EntityNotFoundException("Booking not found: " + bookingId);
        }
        try {
            bookingRepository.deleteById(bookingId);
        } catch (EmptyResultDataAccessException e) {

        }
       publishDeletedBookingEvent(bookingId);
    }

    private void publishPendingBookingEvent(Booking booking) {
        final var event = new PendingBookingEvent(
                booking.getId(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getStaffId()
        );
        publisher.publishEvent(event);
        logger.info("Published PendingBookingEvent for booking {}", booking.getId());
    }

    private void publishUpdateBookingEvent() {

    }

    private void publishDeletedBookingEvent(UUID bookingId) {
        final var event = new DeletedBookingEvent(
                bookingId,
                bookingId
        );
        publisher.publishEvent(event);
        logger.info("Published DeleteBookingEvent for booking {}", bookingId);
    }

}
