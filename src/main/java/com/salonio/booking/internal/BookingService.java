package com.salonio.booking.internal;

import com.salonio.booking.BookingApi;
import com.salonio.booking.dto.BookingResponse;
import com.salonio.booking.dto.CreateBookingRequest;
import com.salonio.booking.dto.UpdateBookingRequest;
import com.salonio.booking.enums.BookingStatus;
import com.salonio.booking.event.*;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
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

    /**
     * @param createBookingRequest
     * @return
     */
    @Transactional
    @Override
    public BookingResponse createBooking(CreateBookingRequest createBookingRequest) {

        final Booking newBooking = new Booking(createBookingRequest.startTime(), createBookingRequest.duration(),
                createBookingRequest.clientId(), createBookingRequest.staffId(),
                createBookingRequest.serviceType(), createBookingRequest.bookingStatus());
        // add try catch
        final Booking savedBooking = bookingRepository.save(newBooking);
        final SavedBookingEvent savedBookingEvent = new SavedBookingEvent(); // TODO
        publisher.publishEvent(savedBookingEvent);
        return mapToBookingResponse(savedBooking);
    }

    /**
     * @param bookingId
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public BookingResponse getBooking(UUID bookingId) {
        final Booking foundBooking = bookingRepository.findById(bookingId)
                .orElseThrow(() -> new EntityNotFoundException("Booking with id " + bookingId + " not found"));
        return mapToBookingResponse(foundBooking);
    }

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
        existing.setDuration(request.duration());
        existing.setClientId(request.clientId());
        existing.setStaffId(request.staffId());
        existing.setServiceType(request.serviceType());
        existing.setStatus(request.bookingStatus());

        Booking updatedBooking;
        try {
            updatedBooking = bookingRepository.save(existing);
        } catch (OptimisticLockingFailureException e) {
            throw new ConcurrentModificationException("Booking with id " + bookingId + " was modified concurrently. Please retry.", e);
        }

        final var updatedBookingResponse = mapToBookingResponse(updatedBooking);

        if (request.bookingStatus() == BookingStatus.CANCELED && oldStatus != BookingStatus.CANCELED) {
            CanceledBookingEvent canceledBookingEvent = new CanceledBookingEvent(request.clientId(), BookingStatus.CANCELED);
            publisher.publishEvent(canceledBookingEvent);
        } else if (request.bookingStatus() == BookingStatus.RESCHEDULED && oldStatus != BookingStatus.RESCHEDULED) {
            RescheduledBookingEvent rescheduledBookingEvent = new RescheduledBookingEvent();
            publisher.publishEvent(rescheduledBookingEvent);
        }
        // else if (request.bookingStatus() == BookingStatus.CONFIRMED && oldStatus != BookingStatus.CONFIRMED) { ... }
        final var updatedBookingEvent = new UpdatedBookingEvent(updatedBookingResponse.id(), BookingStatus.RESCHEDULED);
        publisher.publishEvent(updatedBookingEvent);

        return mapToBookingResponse(updatedBooking);
    }

    /**
     * @param bookingId
     * @return
     */
    @Transactional
    @DeleteMapping("/{bookingId}")
    public void deleteBooking(@PathVariable UUID bookingId) {
        if (!bookingRepository.existsById(bookingId)) {
            throw new EntityNotFoundException("Booking not found: " + bookingId);
        }
        bookingRepository.deleteById(bookingId);
        final var deletedBookingEvent = new DeletedBookingEvent(bookingId, bookingId);
        publisher.publishEvent(deletedBookingEvent);
    }

    /**
     * @param clientId
     * @param dateTime
     * @return
     */
    @Transactional(readOnly = true)
    @Override
    public List<BookingResponse> getBookingByClientIdAndDateTime(UUID clientId, LocalDateTime dateTime) {
        var newDate = dateTime.toLocalDate();
        var startOfTheDat = newDate.atStartOfDay();
        var endOfTheDat = newDate.plusDays(1).atStartOfDay();
        final var foundBookings = bookingRepository.findBookingsForStaffBetween(
                clientId, startOfTheDat, endOfTheDat);

        return foundBookings.stream()
                .map(this::mapToBookingResponse)
                .toList();
    }

    private BookingResponse mapToBookingResponse(Booking booking) {
        if (booking == null) {
            return null;
        }
        return new BookingResponse(
                booking.getId(),
                booking.getStartTime(),
                booking.getDuration(),
                booking.getClientId(),
                booking.getStaffId(),
                booking.getServiceType(),
                booking.getStatus()
        );
    }

}
