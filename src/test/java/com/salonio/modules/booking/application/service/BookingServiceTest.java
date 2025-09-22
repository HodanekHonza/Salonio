package com.salonio.modules.booking.application.service;

import com.salonio.modules.booking.api.dto.BookingResponse;
import com.salonio.modules.booking.api.dto.CreateBookingRequest;
import com.salonio.modules.booking.application.port.out.BookingEventPort;
import com.salonio.modules.booking.application.port.out.BookingPersistencePort;
import com.salonio.modules.booking.domain.Booking;
import com.salonio.modules.booking.exception.BookingExceptions;
import com.salonio.modules.common.event.DomainEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

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

    @BeforeEach
    void setUp() {

    }
    /*
    TODO
     - Event publishing assertions.
     - listBookingsForClient test.
     - Update/delete/cancel tests.
     - Validation edge cases.
     */

    @Test
    void testCreateBooking() {
        UUID clientId = UUID.randomUUID();
        UUID staffId = UUID.randomUUID();
        LocalDateTime startTime = LocalDateTime.of(2025, 7, 20, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, 7, 20, 11, 0);
        String serviceType = "Haircut";

        CreateBookingRequest request = new CreateBookingRequest(
                clientId, staffId, startTime, endTime, serviceType
        );

        Booking expectedBooking = new Booking();
        expectedBooking.setClientId(clientId);
        expectedBooking.setStaffId(staffId);
        expectedBooking.setStartTime(startTime);
        expectedBooking.setEndTime(endTime);
        expectedBooking.setServiceType(serviceType);

        // When
        when(bookingPort.save(any(Booking.class))).thenReturn(expectedBooking);
        when(bookingPort.findById(any(UUID.class))).thenReturn(Optional.of(expectedBooking));
        BookingResponse createdBooking = bookingService.createBooking(request, "");

        // Then
        verify(bookingPort).save(any(Booking.class));
        verify(bookingEventPort).publishPendingBooking(any(Booking.class));

        assertThat(createdBooking).isNotNull();
        assertThat(createdBooking.clientId()).isEqualTo(clientId);
        assertThat(createdBooking.endTime()).isEqualTo(endTime);
        assertThat(createdBooking.serviceType()).isEqualTo(serviceType);
    }

    @Test
    void testListBookingsForClient() {

        // When
//        when(bookingRepository.findByClientId(any(UUID.class))).thenReturn(expectedBooking);

    }

    @Test
    void testGetBookingById_Found() {
        // Given
        UUID bookingId = UUID.randomUUID();
        Booking existingBooking = new Booking();
        existingBooking.setId(bookingId);
        existingBooking.setServiceType("Manicure");

        // When
        when(bookingPort.findById(bookingId)).thenReturn(Optional.of(existingBooking));
        BookingResponse foundBooking = bookingService.getBooking(bookingId);

        // Then
        assertThat(foundBooking.id()).isEqualTo(bookingId);
        assertThat(foundBooking.serviceType()).isEqualTo("Manicure");
        verify(bookingPort).findById(bookingId);
    }
//
    @Test
    void testGetBookingById_NotFound() {
        // Given
        UUID bookingId = UUID.randomUUID();

        // When & Then
        BookingExceptions.BookingNotFoundException exception = assertThrows(
                BookingExceptions.BookingNotFoundException.class,
                () -> bookingService.getBooking(bookingId)
        );
        assertThat(exception.getMessage()).isEqualTo("Booking with id " + bookingId + " not found");
        verify(bookingPort).findById(bookingId);
    }

}