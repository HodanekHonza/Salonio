package com.salonio.booking.internal;

import com.salonio.booking.dto.BookingResponse;
import com.salonio.booking.dto.CreateBookingRequest;
import com.salonio.booking.enums.BookingStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;
import java.time.Duration;
import java.time.LocalDateTime;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
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

//    @Test
//    void testCreateBooking() {
//        UUID clientId = UUID.randomUUID();
//        UUID staffId = UUID.randomUUID();
//        LocalDateTime startTime = LocalDateTime.of(2025, 7, 20, 10, 0);
//        Duration duration = Duration.ofMinutes(60);
//        String serviceType = "Haircut";
//
//        CreateBookingRequest request = new CreateBookingRequest(
//                clientId, staffId, startTime, duration, serviceType, BookingStatus.CREATED
//        );
//
//        Booking expectedBooking = new Booking();
//        expectedBooking.setClientId(clientId);
//        expectedBooking.setStaffId(staffId);
//        expectedBooking.setStartTime(startTime);
//        expectedBooking.setDuration(duration);
//        expectedBooking.setServiceType(serviceType);
//
//        // When
//        when(bookingRepository.save(any(Booking.class))).thenReturn(expectedBooking);
//
//        BookingResponse createdBooking = bookingService.createBooking(request);
//
//        // Then
//        verify(bookingRepository).save(any(Booking.class));
//
//        assertThat(createdBooking).isNotNull();
//        assertThat(createdBooking.clientId()).isEqualTo(clientId);
//        assertThat(createdBooking.duration()).isEqualTo(duration);
//        assertThat(createdBooking.serviceType()).isEqualTo(serviceType);
//    }

    @Test
    void testListBookingsForClient() {

        // When
//        when(bookingRepository.findByClientId(any(UUID.class))).thenReturn(expectedBooking);

    }

//    @Test
//    void testGetBookingById_Found() {
//        // Given
//        UUID bookingId = UUID.randomUUID();
//        Booking existingBooking = new Booking();
//        existingBooking.setId(bookingId);
//        existingBooking.setServiceType("Manicure");
//
//        // When
//        when(bookingRepository.findById(bookingId)).thenReturn(Optional.of(existingBooking));
//
//        Optional<Booking> foundBooking = bookingService.getBookingById(bookingId);
//
//        // Then
//        assertThat(foundBooking).isPresent();
//        assertThat(foundBooking.get().getId()).isEqualTo(bookingId);
//        assertThat(foundBooking.get().getServiceType()).isEqualTo("Manicure");
//        verify(bookingRepository).findById(bookingId); // Verify interaction
//    }
//
//    @Test
//    void testGetBookingById_NotFound() {
//        // Given
//        UUID bookingId = UUID.randomUUID();
//
//        // When
//        when(bookingRepository.findById(bookingId)).thenReturn(Optional.empty());
//
//        Optional<Booking> foundBooking = bookingService.getBookingById(bookingId);
//
//        // Then
//        assertThat(foundBooking).isNotPresent();
//        verify(bookingRepository).findById(bookingId); // Verify interaction
//    }
}