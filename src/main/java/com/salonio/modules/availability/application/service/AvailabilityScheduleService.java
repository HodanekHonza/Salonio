package com.salonio.modules.availability.application.service;

import com.salonio.modules.availability.application.factory.AvailabilityEventFactory;
import com.salonio.modules.availability.domain.event.AvailabilitySchedulingEvent;
import lombok.AllArgsConstructor;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class AvailabilityScheduleService {

    private final AvailabilityEventService availabilityEventService;

    @Scheduled(cron = "0 0 3 * * 7", zone = "Europe/Prague")
    public void scheduleAvailabilityEveryWeek() {
        final AvailabilitySchedulingEvent availabilitySchedulingEvent =
                AvailabilityEventFactory.createAvailabilitySchedulingEvent(true);
        availabilityEventService.publishAvailabilitySchedulingEvent(availabilitySchedulingEvent);
    }

}

