package com.salonio.modules.business.infrastructure.controller;

import com.salonio.modules.business.api.dto.service.ServiceCreateRequest;
import com.salonio.modules.business.api.dto.service.ServiceResponse;
import com.salonio.modules.business.api.dto.service.ServiceUpdateRequest;
import com.salonio.modules.business.application.service.service.ServiceService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@AllArgsConstructor
@RequestMapping("/service")
@RestController
public class ServiceController {

    private final ServiceService reviewService;

    @PostMapping
    ServiceResponse createReview(@Valid @RequestBody ServiceCreateRequest reviewCreateRequest) {
        return reviewService.createService(reviewCreateRequest);
    }

    @GetMapping("/{reviewId}")
    ServiceResponse getReview(@PathVariable UUID reviewId) {
        return reviewService.getService(reviewId);
    }

    @PutMapping("/{reviewId}")
    void updateOffering(@PathVariable UUID reviewId,
                        ServiceUpdateRequest reviewUpdateRequest) {
        reviewService.updateService(reviewId, reviewUpdateRequest);
    }

    @DeleteMapping("/{reviewId}")
    ResponseEntity<Void> deleteOffering(@PathVariable UUID reviewId) {
        reviewService.deleteService(reviewId);
        return ResponseEntity.noContent().build();
    }

}
