package com.salonio.modules.availability.application.service;

import com.salonio.modules.availability.api.dto.AvailabilityResponse;
import com.salonio.modules.availability.api.dto.CreateAvailabilityRequest;
import com.salonio.modules.availability.api.dto.UpdateAvailabilityRequest;
import com.salonio.modules.availability.application.port.out.AvailabilityEventPort;
import com.salonio.modules.availability.application.port.out.AvailabilityPersistencePort;
import com.salonio.modules.availability.domain.Availability;
import com.salonio.modules.availability.exception.AvailabilityExceptions;
import com.salonio.modules.availability.infrastructure.persistence.AvailabilityMapper;
import com.salonio.modules.common.event.DomainEventPublisher;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockedStatic;
import org.mockito.junit.jupiter.MockitoExtension;
import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
import java.util.Optional;
import java.util.UUID;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class AvailabilityServiceTest {

    @Mock
    private DomainEventPublisher publisher;

    @Mock
    private AvailabilityPersistencePort availabilityPersistencePort;

    @Mock
    private AvailabilityEventPort availabilityEventPort;

    @InjectMocks
    private AvailabilityService availabilityService;

    private UUID staffId;
    private UUID businessId;
    private UUID bookingId;
    private UUID clientId;
    private LocalDateTime startTime;
    private LocalDateTime endTime;
    private Availability availability;

    @BeforeEach
    void setUp() {
        staffId = UUID.randomUUID();
        businessId = UUID.randomUUID();
        bookingId = UUID.randomUUID();
        clientId = UUID.randomUUID();
        startTime = LocalDateTime.of(2025, 7, 20, 10, 0);
        endTime = LocalDateTime.of(2025, 7, 20, 11, 0);

        availability = spy(new Availability());
        availability.setId(UUID.randomUUID());
        availability.setStaffId(staffId);
        availability.setBusinessId(businessId);
        availability.setAvailability(true);
        availability.setBookingId(bookingId);
        availability.setClientId(clientId);
        availability.setStartTime(startTime);
        availability.setEndTime(endTime);
    }

    @Test
    void createAvailability_shouldSaveAndReturn() {
        CreateAvailabilityRequest request = new CreateAvailabilityRequest(
                startTime, endTime, staffId, businessId, true, bookingId, clientId
        );

        when(availabilityPersistencePort.save(any(Availability.class))).thenReturn(availability);

        AvailabilityResponse response = availabilityService.createAvailability(request);

        verify(availabilityPersistencePort).save(any(Availability.class));
        assertThat(response).isNotNull();
        assertThat(response.clientId()).isEqualTo(clientId);
        assertThat(response.endTime()).isEqualTo(endTime);
    }

    @Test
    void getAvailability_shouldReturnAvailability() {
        when(availabilityPersistencePort.findById(availability.getId())).thenReturn(Optional.of(availability));

        AvailabilityResponse response = availabilityService.getAvailability(availability.getId());

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(availability.getId());
        verify(availabilityPersistencePort).findById(availability.getId());
    }

    @Test
    void getAvailability_shouldThrowNotFound() {
        UUID id = UUID.randomUUID();
        when(availabilityPersistencePort.findById(id)).thenReturn(Optional.empty());

        assertThatThrownBy(() -> availabilityService.getAvailability(id))
                .isInstanceOf(AvailabilityExceptions.AvailabilityNotFoundException.class);

        verify(availabilityPersistencePort).findById(id);
    }

    @Test
    void updateAvailability_shouldReturnUpdatedAvailability() {
        UpdateAvailabilityRequest request = new UpdateAvailabilityRequest(
                UUID.randomUUID(), UUID.randomUUID(),
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                "haircut"
        );

        when(availabilityPersistencePort.findById(availability.getId())).thenReturn(Optional.of(availability));

        AvailabilityResponse response = availabilityService.updateAvailability(availability.getId(), request);

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(availability.getId());
    }

    @Test
    void deleteAvailability_shouldCallPersistence() {
        availabilityService.deleteAvailability(availability.getId());

        verify(availabilityPersistencePort).deleteById(availability.getId());
    }

    @Test
    void deleteAvailability_shouldThrowNotFound() {
        doThrow(new org.springframework.dao.EmptyResultDataAccessException(1))
                .when(availabilityPersistencePort).deleteById(availability.getId());

        assertThatThrownBy(() -> availabilityService.deleteAvailability(availability.getId()))
                .isInstanceOf(AvailabilityExceptions.AvailabilityNotFoundException.class);

        verify(availabilityPersistencePort).deleteById(availability.getId());
    }

    @Test
    void updateAvailability_shouldThrowConflictOnConcurrentModification() {
        UpdateAvailabilityRequest request = new UpdateAvailabilityRequest(
                UUID.randomUUID(), UUID.randomUUID(),
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                "haircut"
        );

        when(availabilityPersistencePort.findById(availability.getId())).thenReturn(Optional.of(availability));

        try (MockedStatic<AvailabilityMapper> mapperMock = mockStatic(AvailabilityMapper.class)) {
            mapperMock.when(() -> AvailabilityMapper.updateEntity(request, availability))
                    .thenThrow(new ConcurrentModificationException());

            assertThatThrownBy(() -> availabilityService.updateAvailability(availability.getId(), request))
                    .isInstanceOf(AvailabilityExceptions.AvailabilityConflictException.class)
                    .hasMessageContaining("modified concurrently");
        }
    }
}
