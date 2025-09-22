package com.salonio.modules.booking.application.service;

import com.salonio.modules.booking.api.dto.BookingResponse;
import com.salonio.modules.booking.api.dto.CreateBookingRequest;
import com.salonio.modules.booking.api.dto.UpdateBookingRequest;
import com.salonio.modules.booking.application.port.out.BookingEventPort;
import com.salonio.modules.booking.application.port.out.BookingPersistencePort;
import com.salonio.modules.booking.domain.Booking;
import com.salonio.modules.booking.domain.enums.BookingStatus;
import com.salonio.modules.booking.exception.BookingExceptions;
import com.salonio.modules.common.event.DomainEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingServiceTest {

    @Mock
    private DomainEventPublisher publisher;

    @Mock
    private BookingPersistencePort bookingPort;

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
    void testCreateBooking() {
        CreateBookingRequest request = new CreateBookingRequest(clientId, staffId, startTime, endTime, "Haircut");

        // Prepare booking with fixed ID
        UUID bookingId = UUID.randomUUID();
        Booking savedBooking = new Booking();
        savedBooking.setId(bookingId);
        savedBooking.setClientId(clientId);
        savedBooking.setStaffId(staffId);
        savedBooking.setStartTime(startTime);
        savedBooking.setEndTime(endTime);
        savedBooking.setServiceType("Haircut");

        when(bookingPort.save(any(Booking.class))).thenReturn(savedBooking);
        // Stub with exact ID returned from save()
        when(bookingPort.findById(bookingId)).thenReturn(Optional.of(savedBooking));

        BookingResponse response = bookingService.createBooking(request, "");

        verify(bookingPort).save(any(Booking.class));
        verify(bookingEventPort).publishPendingBooking(any(Booking.class));

        assertThat(response).isNotNull();
        assertThat(response.clientId()).isEqualTo(clientId);
        assertThat(response.serviceType()).isEqualTo("Haircut");
        assertThat(response.endTime()).isEqualTo(endTime);
    }

    @Test
    void testGetBookingById_Found() {
        UUID bookingId = UUID.randomUUID();
        Booking existingBooking = new Booking();
        existingBooking.setId(bookingId);
        existingBooking.setServiceType("Manicure");

        when(bookingPort.findById(bookingId)).thenReturn(Optional.of(existingBooking));

        BookingResponse response = bookingService.getBooking(bookingId);

        assertThat(response.id()).isEqualTo(bookingId);
        assertThat(response.serviceType()).isEqualTo("Manicure");
        verify(bookingPort).findById(bookingId);
    }

    @Test
    void testGetBookingById_NotFound() {
        UUID bookingId = UUID.randomUUID();
        when(bookingPort.findById(bookingId)).thenReturn(Optional.empty());

        assertThrows(
                BookingExceptions.BookingNotFoundException.class,
                () -> bookingService.getBooking(bookingId)
        );

        verify(bookingPort).findById(bookingId);
    }

    @Test
    void testUpdateBooking() {
        UUID bookingId = UUID.randomUUID();
        Booking existing = new Booking();
        existing.setId(bookingId);
        existing.setStatus(BookingStatus.PENDING);
        existing.setServiceType("Haircut");

        UpdateBookingRequest request = new UpdateBookingRequest(clientId, staffId, startTime, endTime, "Haircut", BookingStatus.PENDING);

        when(bookingPort.findById(bookingId)).thenReturn(Optional.of(existing));
        when(bookingPort.save(existing)).thenReturn(existing);

        BookingResponse response = bookingService.updateBooking(bookingId, request);

        verify(bookingPort).save(existing);
        verify(bookingEventPort).publishUpdatedBooking(existing, BookingStatus.PENDING);

        assertThat(response.id()).isEqualTo(bookingId);
        assertThat(response.serviceType()).isEqualTo("Haircut");
    }

    @Test
    void testDeleteBooking() {
        UUID bookingId = UUID.randomUUID();

        bookingService.deleteBooking(bookingId);

        verify(bookingPort).deleteById(bookingId);
        verify(bookingEventPort).publishDeletedBooking(bookingId);
    }

    @Test
    void testDeleteBooking_NotFound() {
        UUID bookingId = UUID.randomUUID();
        doThrow(new EmptyResultDataAccessException(1)).when(bookingPort).deleteById(bookingId);

        assertThrows(
                BookingExceptions.BookingNotFoundException.class,
                () -> bookingService.deleteBooking(bookingId)
        );

        verify(bookingPort).deleteById(bookingId);
    }

    @Test
    void testUpdateBooking_ShouldThrowConflictOnOptimisticLock() {
        UUID bookingId = UUID.randomUUID();
        Booking existing = new Booking();
        existing.setId(bookingId);
        existing.setStatus(BookingStatus.PENDING);

        UpdateBookingRequest request = new UpdateBookingRequest(clientId, staffId, startTime, endTime, "Manicure", BookingStatus.PENDING);

        when(bookingPort.findById(bookingId)).thenReturn(Optional.of(existing));
        doThrow(new OptimisticLockingFailureException("Conflict")).when(bookingPort).save(existing);

        assertThrows(
                BookingExceptions.BookingConflictException.class,
                () -> bookingService.updateBooking(bookingId, request)
        );

        verify(bookingPort).save(existing);
    }

}
