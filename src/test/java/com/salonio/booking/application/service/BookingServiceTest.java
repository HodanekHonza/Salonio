package com.salonio.booking.application.service;

import com.salonio.booking.api.dto.BookingResponse;
import com.salonio.booking.api.dto.CreateBookingRequest;
import com.salonio.booking.application.service.BookingService;
import com.salonio.booking.domain.Booking;
import com.salonio.booking.domain.enums.BookingStatus;
import com.salonio.booking.exception.BookingExceptions;
import com.salonio.booking.infrastructure.persistence.BookingRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class) // Integrates Mockito with JUnit 5
class BookingServiceTest {

    @Mock
    private ApplicationEventPublisher publisher;

    @Mock
    private BookingRepository bookingRepository;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        // No explicit MockitoAnnotations.openMocks(this); needed with @ExtendWith(MockitoExtension.class)
    }

    @Test
    void testCreateBooking() {
        UUID clientId = UUID.randomUUID();
        UUID staffId = UUID.randomUUID();
        LocalDateTime startTime = LocalDateTime.of(2025, 7, 20, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, 7, 20, 11, 0);
        String serviceType = "Haircut";

        CreateBookingRequest request = new CreateBookingRequest(
                clientId, staffId, startTime, endTime, serviceType, BookingStatus.CREATED
        );

        Booking expectedBooking = new Booking();
        expectedBooking.setClientId(clientId);
        expectedBooking.setStaffId(staffId);
        expectedBooking.setStartTime(startTime);
        expectedBooking.setEndTime(endTime);
        expectedBooking.setServiceType(serviceType);

        // When
        when(bookingRepository.save(any(Booking.class))).thenReturn(expectedBooking);

        BookingResponse createdBooking = bookingService.createBooking(request, "");

        // Then
        verify(bookingRepository).save(any(Booking.class));

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
//        existingBooking.setId(bookingId);
        existingBooking.setServiceType("Manicure");

        // When
        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(existingBooking));

        BookingResponse foundBooking = bookingService.getBooking(bookingId);
//        Optional<Booking> foundBooking = bookingService.getBooking(bookingId);

        // Then
//        assertThat(foundBooking.getId()).isEqualTo(bookingId);
        assertThat(foundBooking.serviceType()).isEqualTo("Manicure");
        verify(bookingRepository).findById(bookingId); // Verify interaction
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

//        // When & Then
//        assertThatThrownBy(() -> bookingService.getBooking(bookingId))
//                .isInstanceOf(BookingExceptions.BookingNotFoundException.class)
//                .hasMessage("Booking with id " + bookingId + " not found");

        assertThat(exception.getMessage()).isEqualTo("Booking with id " + bookingId + " not found");
        verify(bookingRepository).findById(bookingId); // Verify interaction
    }

}