package com.salonio.modules.business.infrastructure.controller;

import com.salonio.modules.business.api.dto.CreateOfferingRequest;
import com.salonio.modules.business.api.dto.OfferingResponse;
import com.salonio.modules.business.api.dto.UpdateOfferingRequest;
import com.salonio.modules.business.application.service.OfferingService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/offering")
public class OfferingController {

    private final OfferingService offeringService;

    OfferingController(OfferingService offeringService) {
        this.offeringService = offeringService;
    }

    @PostMapping
    OfferingResponse createOffering(@Valid @RequestBody CreateOfferingRequest createOfferingRequest) {
        return offeringService.createOffering(createOfferingRequest);
    }

    @GetMapping("/{offeringId}")
    OfferingResponse getOffering(@PathVariable UUID offeringId) {
        return offeringService.getOffering(offeringId);
    }

    @PutMapping
    void updateOffering(UpdateOfferingRequest updateOfferingRequest) {
        offeringService.updateOffering();
    }

    @DeleteMapping("/{offeringId}")
    ResponseEntity<Void> deleteOffering(@PathVariable UUID offeringId) {
        offeringService.deleteOffering(offeringId);
        return ResponseEntity.noContent().build();
    }

}
