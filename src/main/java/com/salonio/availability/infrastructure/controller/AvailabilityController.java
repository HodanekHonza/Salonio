package com.salonio.availability.infrastructure.controller;

import com.salonio.availability.api.dto.AvailabilityResponse;
import com.salonio.availability.api.dto.CreateAvailabilityRequest;
import com.salonio.availability.api.dto.UpdateAvailabilityRequest;
import com.salonio.availability.application.service.AvailabilityService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    private final AvailabilityService availabilityService;

    AvailabilityController(AvailabilityService availabilityService) {
        this.availabilityService = availabilityService;
    }

    @PostMapping
    AvailabilityResponse createAvailability(@Valid @RequestBody CreateAvailabilityRequest createAvailabilityRequest) {
        return availabilityService.createAvailability(createAvailabilityRequest);
    }

    @GetMapping("/{availabilityId}")
    AvailabilityResponse getAvailability(@PathVariable UUID availabilityId) {
        return availabilityService.getAvailability(availabilityId);
    }

    @PutMapping("/{availabilityId}")
    AvailabilityResponse updateAvailability(@PathVariable UUID availabilityId,
                                            @Valid @RequestBody UpdateAvailabilityRequest updateAvailabilityRequest) {
        return availabilityService.updateAvailability(availabilityId, updateAvailabilityRequest);
    }

    @DeleteMapping("/{availabilityId}")
    ResponseEntity<Void> deleteAvailability(@PathVariable UUID availabilityId) {
        availabilityService.deleteAvailability(availabilityId);
        return ResponseEntity.noContent().build();
    }

}
