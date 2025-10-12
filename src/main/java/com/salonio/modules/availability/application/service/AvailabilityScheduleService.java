package com.salonio.modules.availability.application.service;

import com.salonio.modules.availability.api.dto.CreateAvailabilityRequest;
import com.salonio.modules.common.event.DomainEventPublisher;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;

import java.util.List;

@AllArgsConstructor
@Service
public class AvailabilityScheduleService {

    private final DomainEventPublisher publisher;

    private static final Logger logger = LoggerFactory.getLogger(AvailabilityScheduleService.class);

    @Scheduled(cron = "0 0 3 * * 7", zone = "Europe/Prague")
    public void scheduleAvailabilityEveryWeek() {
        // 1.  List businesses, look for the ones that have the scheduling attribute bool set to true
        // 2. For each business look thru their already created availabilities for the week
    }
}

