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
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.ConcurrentModificationException;
import java.util.List;
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
    void listAvailabilityByDateAndBusinessId_shouldReturnMultipleMappedPage() {
        LocalDate targetDate = LocalDate.of(2025, 7, 20);
        PageRequest pageable = PageRequest.of(0, 10);

        Availability availability2 = spy(new Availability());
        availability2.setId(UUID.randomUUID());
        availability2.setStaffId(staffId);
        availability2.setBusinessId(businessId);
        availability2.setAvailability(true);
        availability2.setStartTime(startTime.plusHours(1));
        availability2.setEndTime(endTime.plusHours(1));

        Availability availability3 = spy(new Availability());
        availability3.setId(UUID.randomUUID());
        availability3.setStaffId(staffId);
        availability3.setBusinessId(businessId);
        availability3.setAvailability(true);
        availability3.setStartTime(startTime.plusHours(2));
        availability3.setEndTime(endTime.plusHours(2));

        Page<Availability> mockPage = new PageImpl<>(List.of(availability, availability2, availability3));
        when(availabilityPersistencePort.findAvailabilityByBusinessIdAndDate(businessId, targetDate, pageable))
                .thenReturn(mockPage);

        Page<AvailabilityResponse> responsePage = availabilityService
                .listAvailabilityByDateAndBusinessId(businessId, targetDate, pageable);

        verify(availabilityPersistencePort).findAvailabilityByBusinessIdAndDate(businessId, targetDate, pageable);

        assertThat(responsePage).isNotNull();
        assertThat(responsePage.getTotalElements()).isEqualTo(3);

        AvailabilityResponse response1 = responsePage.getContent().get(0);
        AvailabilityResponse response2 = responsePage.getContent().get(1);
        AvailabilityResponse response3 = responsePage.getContent().get(2);

        assertThat(response1.id()).isEqualTo(availability.getId());
        assertThat(response2.id()).isEqualTo(availability2.getId());
        assertThat(response3.id()).isEqualTo(availability3.getId());

        assertThat(response1.startTime()).isEqualTo(availability.getStartTime());
        assertThat(response2.startTime()).isEqualTo(availability2.getStartTime());
        assertThat(response3.startTime()).isEqualTo(availability3.getStartTime());

        assertThat(response1.endTime()).isEqualTo(availability.getEndTime());
        assertThat(response2.endTime()).isEqualTo(availability2.getEndTime());
        assertThat(response3.endTime()).isEqualTo(availability3.getEndTime());
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
        when(availabilityPersistencePort.save(any(Availability.class))).thenReturn(availability);

        AvailabilityResponse response = availabilityService.updateAvailability(availability.getId(), request);

        assertThat(response).isNotNull();
        assertThat(response.id()).isEqualTo(availability.getId());

        verify(availabilityPersistencePort).findById(availability.getId());
        verify(availabilityPersistencePort).save(availability);
    }


    @Test
    void deleteAvailability_shouldCallPersistence() {
        availabilityService.deleteAvailability(availability.getId());

        verify(availabilityPersistencePort).deleteById(availability.getId());
    }

    @Test
    void deleteAvailability_shouldThrowNotFound() {
        UUID availabilityId = availability.getId();
        doThrow(new org.springframework.dao.EmptyResultDataAccessException(1))
                .when(availabilityPersistencePort).deleteById(availabilityId);

        assertThatThrownBy(() -> availabilityService.deleteAvailability(availabilityId))
                .isInstanceOf(AvailabilityExceptions.AvailabilityNotFoundException.class);

        verify(availabilityPersistencePort).deleteById(availabilityId);
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
            mapperMock.when(() -> availability.updateEntity(request))
                    .thenThrow(new ConcurrentModificationException());

            assertThatThrownBy(() -> availabilityService.updateAvailability(availability.getId(), request))
                    .isInstanceOf(AvailabilityExceptions.AvailabilityConflictException.class)
                    .hasMessageContaining("modified concurrently");
        }
    }

}
