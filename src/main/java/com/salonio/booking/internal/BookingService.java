package com.salonio.booking.internal;

import com.salonio.booking.BookingApi;
import com.salonio.booking.event.PendingBookingEvent;
import com.salonio.booking.dto.BookingResponse;
import com.salonio.booking.dto.CreateBookingRequest;
import com.salonio.booking.dto.UpdateBookingRequest;
import com.salonio.booking.enums.BookingStatus;
import com.salonio.booking.event.*;
import com.salonio.booking.exception.BookingConflictException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import java.util.ConcurrentModificationException;
import java.util.UUID;

@Service
class BookingService implements BookingApi {

    private final ApplicationEventPublisher publisher;
    private final BookingRepository bookingRepository;

    private static final Logger logger = LoggerFactory.getLogger(BookingService.class);

    BookingService(ApplicationEventPublisher publisher, BookingRepository bookingRepository) {
        this.publisher = publisher;
        this.bookingRepository = bookingRepository;
    }

    /**
     */
    @Transactional
    @Override
    public BookingResponse createBooking(CreateBookingRequest createBookingRequest, String authorizationCode) {

        final Booking newBooking = new Booking(createBookingRequest.startTime(), createBookingRequest.endTime(),
                createBookingRequest.clientId(), createBookingRequest.staffId(),
                createBookingRequest.serviceType(), BookingStatus.PENDING);

        Booking savedBooking;

        try {
            logger.info("Creating a new booking with the following request: {}", createBookingRequest);
            savedBooking = bookingRepository.save(newBooking);
            logger.info("Booking saved successfully id: {}", savedBooking.getId());
        } catch (OptimisticLockingFailureException e) {
            logger.error("Creating booking failed");
            throw new BookingConflictException("Creating booking conflict");
        }

        final PendingBookingEvent pendingBookingEvent = new PendingBookingEvent(
                newBooking.getId(), newBooking.getStartTime(), newBooking.getEndTime(), newBooking.getStaffId());


        logger.info("publishing pendingBookingEvent");
        publisher.publishEvent(pendingBookingEvent);

        logger.info("Booking event published -> mappingBookingResponses");
        return mapToBookingResponse(savedBooking);
    }

    /**
     * @param bookingId
     * @return
     */
    @Transactional(readOnly = true) // not needed
    @Override
    public BookingResponse getBooking(UUID bookingId) {
        final Booking foundBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking with id " + bookingId + " not found"));
        return mapToBookingResponse(foundBooking);
    }
    // test

    /**
     * @param bookingId
     * @param request
     * @return
     */
    @Transactional
    @Override
    public BookingResponse updateBooking(UUID bookingId, UpdateBookingRequest request) {
        Booking existing = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking with id " + bookingId + " not found"));

        BookingStatus oldStatus = existing.getStatus();

        existing.setStartTime(request.startTime());
        existing.setEndTime(request.endTime());
        existing.setClientId(request.clientId());
        existing.setStaffId(request.staffId());
        existing.setServiceType(request.serviceType());
        existing.setStatus(request.status());

        Booking updatedBooking;
        try {
            updatedBooking = bookingRepository.save(existing);
        } catch (OptimisticLockingFailureException e) {
            throw new ConcurrentModificationException("Booking with id " + bookingId + " was modified concurrently. Please retry.", e);
        }

        final var updatedBookingResponse = mapToBookingResponse(updatedBooking);

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

        return mapToBookingResponse(updatedBooking);
    }

    /**
     * @param bookingId
     * @return
     */
    @Transactional
    @Override
    public void deleteBooking(@PathVariable UUID bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new EntityNotFoundException("Booking not found: " + bookingId);
        }
        bookingRepository.deleteById(bookingId);
        final var deletedBookingEvent = new DeletedBookingEvent(bookingId, bookingId);
        publisher.publishEvent(deletedBookingEvent);
    }


    private BookingResponse mapToBookingResponse(Booking booking) {
        if (booking == null) {
            return null;
        }
        return new BookingResponse(
                booking.getId(),
                booking.getStartTime(),
                booking.getEndTime(),
                booking.getClientId(),
                booking.getStaffId(),
                booking.getServiceType(),
                booking.getStatus()
        );
    }

}
