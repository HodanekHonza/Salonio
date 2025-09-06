package com.salonio.availability.internal;

import com.salonio.availability.dto.AvailabilityResponse;
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
    AvailabilityResponse createAvailability(@RequestBody Availability availability) {
        return availabilityService.createAvailability();
    }

    @GetMapping("/{availabilityId}")
    AvailabilityResponse getAvailability(@PathVariable UUID availabilityId) {
       return availabilityService.getAvailability(availabilityId);
    }

    @PutMapping()
    AvailabilityResponse updateAvailability(@RequestBody Availability availability) {
        return availabilityService.updateAvailability();
    }

    @DeleteMapping()
    void deleteAvailability(@RequestParam UUID staffId) {
    }

}
