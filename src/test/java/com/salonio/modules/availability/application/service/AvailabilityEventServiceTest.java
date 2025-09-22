package com.salonio.modules.availability.application.service;

import com.salonio.modules.availability.domain.event.AvailabilitySlotConfirmedEvent;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.context.ApplicationEventPublisher;

import static org.mockito.Mockito.verify;

@ExtendWith(MockitoExtension.class)
class AvailabilityEventServiceTest {

    @Mock
    private ApplicationEventPublisher applicationEventPublisher;

    @InjectMocks
    private AvailabilityEventService availabilityEventService;

    @Test
    void testPublishAvailabilitySlotConfirmedEvent() {
        // Arrange
        AvailabilitySlotConfirmedEvent event = new AvailabilitySlotConfirmedEvent(
                java.util.UUID.randomUUID()
        );

        // Act
        availabilityEventService.publishAvailabilitySlotConfirmedEvent(event);

        // Assert
        verify(applicationEventPublisher).publishEvent(event);
    }

}
