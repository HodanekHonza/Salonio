package com.salonio.modules.booking.application.service;

import com.salonio.modules.booking.api.dto.BookingResponse;
import com.salonio.modules.booking.api.dto.CreateBookingRequest;
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
import org.springframework.context.ApplicationEventPublisher;

import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@ExtendWith(MockitoExtension.class)
class BookingEventServiceTest {

    @Mock
    private ApplicationEventPublisher publisher;

    @Mock
    private BookingPersistencePort bookingPort;

    @Mock
    private BookingEventPort bookingEventPort;

    @InjectMocks
    private BookingService bookingService;

    @BeforeEach
    void setUp() {
        // No explicit MockitoAnnotations.openMocks(this); needed with @ExtendWith(MockitoExtension.class)
    }

    @Test
    void testCreateBooking() {

    }

}