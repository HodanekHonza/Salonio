package com.salonio.modules.availability.infrastructure.controller;

import com.salonio.modules.availability.api.AvailabilityApi;
import com.salonio.modules.availability.api.dto.AvailabilityResponse;
import com.salonio.modules.availability.api.dto.CreateAvailabilityRequest;
import com.salonio.modules.availability.api.dto.UpdateAvailabilityRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/availability")
public class AvailabilityController {

    private final AvailabilityApi availabilityApi;

    @PostMapping
    AvailabilityResponse createAvailability(@Valid @RequestBody CreateAvailabilityRequest createAvailabilityRequest) {
        return availabilityApi.createAvailability(createAvailabilityRequest);
    }

    @GetMapping("/{availabilityId}")
    AvailabilityResponse getAvailability(@PathVariable UUID availabilityId) {
        return availabilityApi.getAvailability(availabilityId);
    }

    @PutMapping("/{availabilityId}")
    AvailabilityResponse updateAvailability(@PathVariable UUID availabilityId,
                                            @Valid @RequestBody UpdateAvailabilityRequest updateAvailabilityRequest) {
        return availabilityApi.updateAvailability(availabilityId, updateAvailabilityRequest);
    }

    @DeleteMapping("/{availabilityId}")
    ResponseEntity<Void> deleteAvailability(@PathVariable UUID availabilityId) {
        availabilityApi.deleteAvailability(availabilityId);
        return ResponseEntity.noContent().build();
    }

}
