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
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
public class AvailabilityServiceTest {

    @Mock
    private DomainEventPublisher publisher;

    @Mock
    private AvailabilityPersistencePort availabilityPersistencePort;

    @Mock
    private AvailabilityEventPort availabilityEventPort;

    @InjectMocks
    private AvailabilityService availabilityService;


    @Test
    void createAvailability() {
        UUID staffId = UUID.randomUUID();
        UUID businessId = UUID.randomUUID();
        boolean availability = true;
        UUID bookingId = UUID.randomUUID();
        UUID clientId = UUID.randomUUID();
        LocalDateTime startTime = LocalDateTime.of(2025, 7, 20, 10, 0);
        LocalDateTime endTime = LocalDateTime.of(2025, 7, 20, 11, 0);

        CreateAvailabilityRequest request = new CreateAvailabilityRequest(
              startTime, endTime, staffId, businessId, availability, bookingId, clientId
        );

        Availability expectedAvailability = new Availability();
        expectedAvailability.setId(UUID.randomUUID());
        expectedAvailability.setStartTime(startTime);
        expectedAvailability.setEndTime(endTime);
        expectedAvailability.setStaffId(staffId);
        expectedAvailability.setBusinessId(businessId);
        expectedAvailability.setAvailability(availability);
        expectedAvailability.setBookingId(bookingId);
        expectedAvailability.setClientId(clientId);

        // When
        when(availabilityPersistencePort.save(any(Availability.class))).thenReturn(expectedAvailability);
        AvailabilityResponse response = availabilityService.createAvailability(request);

        // Then
        verify(availabilityPersistencePort).save(any(Availability.class));

        // Assert
        assertThat(response).isNotNull();
        assertThat(response.clientId()).isEqualTo(clientId);
        assertThat(response.endTime()).isEqualTo(endTime);
    }


    @Test
    void getAvailability_shouldReturnAvailability() {
        UUID id = UUID.randomUUID();
        Availability availability = new Availability();
        availability.setId(id);

        when(availabilityPersistencePort.findById(id)).thenReturn(Optional.of(availability));

        AvailabilityResponse response = availabilityService.getAvailability(id);

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
        verify(availabilityPersistencePort).findById(id);
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
        UUID id = UUID.randomUUID();
        Availability existing = new Availability();
        existing.setId(id);

        UpdateAvailabilityRequest request = new UpdateAvailabilityRequest(
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                "haircut"
        );

        Availability updated = new Availability();
        updated.setId(id);

        // Mock persistence
        when(availabilityPersistencePort.findById(id)).thenReturn(Optional.of(existing));

        // Since mapper is static, you may need to mock it with MockedStatic if it applies
        AvailabilityResponse response = availabilityService.updateAvailability(id, request);

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(id);
    }

    @Test
    void deleteAvailability_shouldCallPersistence() {
        UUID id = UUID.randomUUID();

        availabilityService.deleteAvailability(id);

        verify(availabilityPersistencePort).deleteById(id);
    }

    @Test
    void deleteAvailability_shouldThrowNotFound() {
        UUID id = UUID.randomUUID();
        doThrow(new org.springframework.dao.EmptyResultDataAccessException(1))
                .when(availabilityPersistencePort).deleteById(id);

        assertThatThrownBy(() -> availabilityService.deleteAvailability(id))
                .isInstanceOf(AvailabilityExceptions.AvailabilityNotFoundException.class);

        verify(availabilityPersistencePort).deleteById(id);
    }

    @Test
    void updateAvailability_shouldThrowConflictOnConcurrentModification() {
        UUID id = UUID.randomUUID();
        Availability existing = new Availability();
        existing.setId(id);

        UpdateAvailabilityRequest request = new UpdateAvailabilityRequest(
                UUID.randomUUID(),
                UUID.randomUUID(),
                LocalDateTime.now().plusHours(1),
                LocalDateTime.now().plusHours(2),
                "haircut"
        );

        // Mock persistence to return the existing availability
        when(availabilityPersistencePort.findById(id)).thenReturn(Optional.of(existing));

        // Mock the static mapper method to throw ConcurrentModificationException
        try (MockedStatic<AvailabilityMapper> mapperMock = mockStatic(AvailabilityMapper.class)) {
            mapperMock.when(() -> AvailabilityMapper.updateEntity(request, existing))
                    .thenThrow(new ConcurrentModificationException());

            assertThatThrownBy(() -> availabilityService.updateAvailability(id, request))
                    .isInstanceOf(AvailabilityExceptions.AvailabilityConflictException.class)
                    .hasMessageContaining("modified concurrently");
        }
    }

}
