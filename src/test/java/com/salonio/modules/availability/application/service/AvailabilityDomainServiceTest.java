package com.salonio.modules.availability.application.service;

import com.salonio.modules.availability.application.port.out.AvailabilityEventPort;
import com.salonio.modules.availability.application.port.out.AvailabilityPersistencePort;
import com.salonio.modules.availability.domain.Availability;
import com.salonio.modules.availability.domain.event.AvailabilitySlotConfirmedEvent;
import com.salonio.modules.availability.exception.AvailabilityExceptions;
import com.salonio.modules.booking.domain.event.PendingBookingEvent;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.dao.OptimisticLockingFailureException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.UUID;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AvailabilityDomainServiceTest {

    @Mock
    private AvailabilityEventPort availabilityEventPort;

    @Mock
    private AvailabilityPersistencePort availabilityPersistencePort;

    @InjectMocks
    private AvailabilityDomainService availabilityDomainService;

    private PendingBookingEvent bookingEvent;
    private UUID staffId;
    private UUID clientId;
    private UUID bookingId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;

    @BeforeEach
    void setUp() {
        staffId = UUID.randomUUID();
        clientId = UUID.randomUUID();
        bookingId = UUID.randomUUID();
        startTime = LocalDateTime.of(2025, 7, 20, 10, 0);
        endTime = LocalDateTime.of(2025, 7, 20, 11, 0);

        bookingEvent = new PendingBookingEvent(
                bookingId, startTime, endTime, staffId, clientId
        );
    }

    @Test
    void testCheckAvailability_Success() {
        Availability slot = mock(Availability.class);
        Availability confirmedSlot = mock(Availability.class);

        when(availabilityPersistencePort.findSpecificAvailableSlot(staffId, startTime, endTime))
                .thenReturn(Optional.of(slot));
        when(slot.confirm(bookingId, clientId)).thenReturn(confirmedSlot);

        availabilityDomainService.checkAvailability(bookingEvent);

        verify(availabilityPersistencePort).save(confirmedSlot);
        verify(slot).confirm(bookingId, clientId);
        verify(availabilityEventPort).publishAvailabilitySlotConfirmedEvent(any(AvailabilitySlotConfirmedEvent.class));
    }

    @Test
    void testCheckAvailability_NoSlotFound() {
        when(availabilityPersistencePort.findSpecificAvailableSlot(staffId, startTime, endTime))
                .thenReturn(Optional.empty());

        assertThrows(
                AvailabilityExceptions.AvailabilityNotFoundException.class,
                () -> availabilityDomainService.checkAvailability(bookingEvent)
        );

        verify(availabilityEventPort, never()).publishAvailabilitySlotConfirmedEvent(any());
    }

    @Test
    void testCheckAvailability_ConflictException() {
        Availability slot = mock(Availability.class);

        when(availabilityPersistencePort.findSpecificAvailableSlot(staffId, startTime, endTime))
                .thenReturn(Optional.of(slot));
        when(slot.confirm(bookingId, clientId))
                .thenThrow(new OptimisticLockingFailureException("Conflict"));

        assertThrows(
                AvailabilityExceptions.AvailabilityConflictException.class,
                () -> availabilityDomainService.checkAvailability(bookingEvent)
        );

        verify(availabilityPersistencePort, never()).save(any());
        verify(availabilityEventPort, never()).publishAvailabilitySlotConfirmedEvent(any());
    }

}
