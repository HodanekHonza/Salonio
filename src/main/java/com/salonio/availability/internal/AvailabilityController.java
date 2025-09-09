package com.salonio.availability.internal;

import com.salonio.availability.dto.AvailabilityResponse;
import com.salonio.availability.dto.CreateAvailabilityRequest;
import com.salonio.availability.dto.UpdateAvailabilityRequest;
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

    @PutMapping()
    AvailabilityResponse updateAvailability(@Valid @RequestBody UpdateAvailabilityRequest updateAvailabilityRequest) {
        return availabilityService.updateAvailability(updateAvailabilityRequest);
    }

    @DeleteMapping("/{availabilityId}")
    ResponseEntity<Void> deleteAvailability(@PathVariable UUID availabilityId) {
        availabilityService.deleteAvailability(availabilityId);
        return ResponseEntity.noContent().build();
    }

}
