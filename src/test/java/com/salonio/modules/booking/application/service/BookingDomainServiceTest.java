package com.salonio.modules.booking.application.service;

import com.salonio.modules.availability.domain.event.AvailabilitySlotConfirmedEvent;
import com.salonio.modules.availability.domain.event.AvailabilitySlotNotFoundEvent;
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
import org.springframework.dao.OptimisticLockingFailureException;

import java.util.Optional;
import java.util.UUID;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class BookingDomainServiceTest {

    @Mock
    private DomainEventPublisher publisher;

    @Mock
    private BookingPersistencePort bookingPort;

    @InjectMocks
    private BookingDomainService bookingDomainService;

    private UUID bookingId;
    private Booking booking;

    @BeforeEach
    void setUp() {
        bookingId = UUID.randomUUID();
        booking = spy(new Booking());
        booking.setId(bookingId);
    }

    @Test
    void testSaveBookingResult_Success() {
        // Arrange
        AvailabilitySlotConfirmedEvent event = new AvailabilitySlotConfirmedEvent(bookingId);
        when(bookingPort.findById(bookingId)).thenReturn(Optional.of(booking));
        when(bookingPort.save(booking)).thenReturn(booking);

        // Act
        bookingDomainService.saveBookingResult(event);

        // Assert
        verify(bookingPort).findById(bookingId);
        verify(bookingPort).save(booking);
    }

    @Test
    void testSaveBookingResult_BookingNotFound() {
        AvailabilitySlotConfirmedEvent event = new AvailabilitySlotConfirmedEvent(bookingId);
        when(bookingPort.findById(bookingId)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> bookingDomainService.saveBookingResult(event))
                .isInstanceOf(BookingExceptions.BookingNotFoundException.class);

        verify(bookingPort).findById(bookingId);
        verify(bookingPort, never()).save(any());
    }

    @Test
    void testSaveBookingResult_OptimisticLockConflict() {
        AvailabilitySlotConfirmedEvent event = new AvailabilitySlotConfirmedEvent(bookingId);
        when(bookingPort.findById(bookingId)).thenReturn(Optional.of(booking));
        doThrow(new OptimisticLockingFailureException("Conflict")).when(booking).confirm();

        assertThatThrownBy(() -> bookingDomainService.saveBookingResult(event))
                .isInstanceOf(BookingExceptions.BookingConflictException.class);

        verify(bookingPort).findById(bookingId);
        verify(bookingPort, never()).save(any());
    }

    @Test
    void testDeleteBookingResult_Success() {
        AvailabilitySlotNotFoundEvent event = new AvailabilitySlotNotFoundEvent(bookingId);

        bookingDomainService.deleteBookingResult(event);

        verify(bookingPort).deleteById(bookingId);
    }

    @Test
    void testDeleteBookingResult_OptimisticLockConflict() {
        AvailabilitySlotNotFoundEvent event = new AvailabilitySlotNotFoundEvent(bookingId);
        doThrow(new OptimisticLockingFailureException("Conflict")).when(bookingPort).deleteById(bookingId);

        assertThatThrownBy(() -> bookingDomainService.deleteBookingResult(event))
                .isInstanceOf(BookingExceptions.BookingConflictException.class);

        verify(bookingPort).deleteById(bookingId);
    }

}
