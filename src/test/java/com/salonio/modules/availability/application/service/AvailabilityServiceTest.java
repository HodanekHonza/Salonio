package com.salonio.modules.availability.application.service;

import com.salonio.modules.availability.application.port.out.AvailabilityEventPort;
import com.salonio.modules.availability.application.port.out.AvailabilityPersistencePort;
import com.salonio.modules.common.event.DomainEventPublisher;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

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

    }
}
