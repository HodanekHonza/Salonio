package com.salonio.modules.booking.application.service;

import com.salonio.modules.booking.api.dto.BookingResponse;
import com.salonio.modules.booking.api.dto.CreateBookingRequest;
import com.salonio.modules.booking.api.dto.UpdateBookingRequest;
import com.salonio.modules.booking.application.port.out.BookingEventPort;
import com.salonio.modules.booking.application.port.out.BookingPersistencePort;
import com.salonio.modules.booking.domain.Booking;
import com.salonio.modules.booking.domain.enums.BookingStatus;
import com.salonio.modules.booking.exception.BookingExceptions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.Pageable;

import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.*;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private BookingPersistencePort bookingPersistencePort;

    @Mock
    private BookingEventPort bookingEventPort;

    @InjectMocks
    private BookingService bookingService;

    private UUID clientId;
    private UUID staffId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @BeforeEach
    void setUp() {
        clientId = UUID.randomUUID();
        staffId = UUID.randomUUID();
        startTime = LocalDateTime.of(2025, 7, 20, 10, 0);
        endTime = LocalDateTime.of(2025, 7, 20, 11, 0);
    }

    @Test
    void shouldCreateBookingAndPublishPendingEvent() {
        CreateBookingRequest request = new CreateBookingRequest(clientId, staffId, startTime, endTime, "Haircut");

        Booking savedBooking = new Booking();
        savedBooking.setId(UUID.randomUUID());
        savedBooking.setClientId(clientId);
        savedBooking.setStaffId(staffId);
        savedBooking.setStartTime(startTime);
        savedBooking.setEndTime(endTime);
        savedBooking.setServiceType("Haircut");
        savedBooking.setStatus(BookingStatus.PENDING);

        when(bookingPersistencePort.save(any(Booking.class))).thenReturn(savedBooking);
        when(bookingPersistencePort.findById(savedBooking.getId())).thenReturn(Optional.of(savedBooking));

        BookingResponse response = bookingService.createBooking(request, "");

        verify(bookingPersistencePort).save(any(Booking.class));
        verify(bookingEventPort).publishPendingBooking(savedBooking);

        assertThat(response).isNotNull();
        assertThat(response.clientId()).isEqualTo(clientId);
        assertThat(response.serviceType()).isEqualTo("Haircut");
    }

    @Test
    void shouldReturnBookingById() {
        UUID bookingId = UUID.randomUUID();
        Booking booking = new Booking();
        booking.setId(bookingId);
        booking.setServiceType("Massage");

        when(bookingPersistencePort.findById(bookingId)).thenReturn(Optional.of(booking));

        BookingResponse response = bookingService.getBooking(bookingId);

        assertThat(response.id()).isEqualTo(bookingId);
        assertThat(response.serviceType()).isEqualTo("Massage");
        verify(bookingPersistencePort).findById(bookingId);
    }

    @Test
    void shouldThrowWhenBookingNotFound() {
        UUID bookingId = UUID.randomUUID();
        when(bookingPersistencePort.findById(bookingId)).thenReturn(Optional.empty());

        assertThrows(
                BookingExceptions.BookingNotFoundException.class,
                () -> bookingService.getBooking(bookingId)
        );
    }

    @Test
    void shouldReturnBookingsByClientId() {
        Booking b1 = new Booking();
        b1.setId(UUID.randomUUID());
        b1.setServiceType("Massage");

        Booking b2 = new Booking();
        b2.setId(UUID.randomUUID());
        b2.setServiceType("Haircut");

        Page<Booking> page = new PageImpl<>(List.of(b1, b2));
        when(bookingPersistencePort.findByClientId(eq(clientId), any(Pageable.class))).thenReturn(page);

        Page<BookingResponse> result = bookingService.getBookingsByClientId(clientId, Pageable.ofSize(10));

        assertThat(result).hasSize(2);
        assertThat(result.getContent())
                .extracting(BookingResponse::serviceType)
                .containsExactlyInAnyOrder("Massage", "Haircut");
    }

    @Test
    void shouldReturnBookingsByStaffId() {
        Booking b1 = new Booking();
        b1.setId(UUID.randomUUID());
        b1.setServiceType("Pedicure");

        Booking b2 = new Booking();
        b2.setId(UUID.randomUUID());
        b2.setServiceType("Coloring");

        Page<Booking> page = new PageImpl<>(List.of(b1, b2));
        when(bookingPersistencePort.findByStaffId(eq(staffId), any(Pageable.class))).thenReturn(page);

        Page<BookingResponse> result = bookingService.getBookingsByStaffId(staffId, Pageable.ofSize(10));

        assertThat(result).hasSize(2);
        assertThat(result.getContent())
                .extracting(BookingResponse::serviceType)
                .containsExactlyInAnyOrder("Pedicure", "Coloring");
    }

    @Test
    void shouldUpdateBookingAndPublishEvent() {
        UUID bookingId = UUID.randomUUID();

        // Fully populated old booking
        Booking oldBooking = new Booking();
        oldBooking.setId(bookingId);
        oldBooking.setClientId(clientId);
        oldBooking.setStaffId(staffId);
        oldBooking.setStartTime(startTime);
        oldBooking.setEndTime(endTime);
        oldBooking.setServiceType("Massage");
        oldBooking.setStatus(BookingStatus.PENDING);

        // Updated booking (returned from save)
        Booking updatedBooking = new Booking();
        updatedBooking.setId(bookingId);
        updatedBooking.setClientId(clientId);
        updatedBooking.setStaffId(staffId);
        updatedBooking.setStartTime(startTime.plusHours(1));
        updatedBooking.setEndTime(endTime.plusHours(1));
        updatedBooking.setServiceType("Massage");
        updatedBooking.setStatus(BookingStatus.PENDING);

        UpdateBookingRequest request = new UpdateBookingRequest(
                clientId, staffId, startTime.plusHours(1), endTime.plusHours(1), "Massage", BookingStatus.PENDING
        );

        when(bookingPersistencePort.findById(bookingId)).thenReturn(Optional.of(oldBooking));
        when(bookingPersistencePort.save(any(Booking.class))).thenReturn(updatedBooking);

        BookingResponse response = bookingService.updateBooking(bookingId, request);

        verify(bookingEventPort).publishUpdatedBooking(
                eq(updatedBooking),
                argThat(b ->
                        b.getId().equals(bookingId)
                                && b.getClientId().equals(clientId)
                                && b.getStaffId().equals(staffId)
                                && b.getStartTime().equals(startTime)
                                && b.getEndTime().equals(endTime)
                                && b.getServiceType().equals("Massage")
                                && b.getStatus() == BookingStatus.PENDING
                )
        );

        assertThat(response.id()).isEqualTo(bookingId);
    }


    @Test
    void shouldThrowConflictWhenSaveFails() {
        UUID bookingId = UUID.randomUUID();
        Booking booking = new Booking();
        booking.setId(bookingId);

        UpdateBookingRequest req = new UpdateBookingRequest(clientId, staffId, startTime, endTime, "Haircut", BookingStatus.PENDING);

        when(bookingPersistencePort.findById(bookingId)).thenReturn(Optional.of(booking));
        when(bookingPersistencePort.save(any(Booking.class))).thenThrow(new OptimisticLockingFailureException("fail"));

        assertThrows(BookingExceptions.BookingConflictException.class, () ->
                bookingService.updateBooking(bookingId, req)
        );
    }

    @Test
    void shouldDeleteBookingAndPublishEvent() {
        UUID bookingId = UUID.randomUUID();

        bookingService.deleteBooking(bookingId);

        verify(bookingPersistencePort).deleteById(bookingId);
        verify(bookingEventPort).publishDeletedBooking(bookingId);
    }

    @Test
    void shouldThrowWhenDeletingNonExistentBooking() {
        UUID bookingId = UUID.randomUUID();
        doThrow(new EmptyResultDataAccessException(1)).when(bookingPersistencePort).deleteById(bookingId);

        assertThrows(BookingExceptions.BookingNotFoundException.class, () ->
                bookingService.deleteBooking(bookingId)
        );
    }

}
