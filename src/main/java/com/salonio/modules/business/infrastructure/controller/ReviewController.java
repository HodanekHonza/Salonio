package com.salonio.modules.business.infrastructure.controller;

import com.salonio.modules.business.api.dto.review.ReviewCreateRequest;
import com.salonio.modules.business.api.dto.review.ReviewResponse;
import com.salonio.modules.business.api.dto.review.ReviewUpdateRequest;
import com.salonio.modules.business.application.service.review.ReviewService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/offering")
public class ReviewController {

    private final ReviewService offeringService;

    ReviewController(ReviewService offeringService) {
        this.offeringService = offeringService;
    }
//
//    @PostMapping
//    ReviewResponse createOffering(@Valid @RequestBody ReviewCreateRequest reviewCreateRequest) {
//        return offeringService.createReview(reviewCreateRequest);
//    }
//
//    @GetMapping("/{reviewId}")
//    ReviewResponse getOffering(@PathVariable UUID reviewId) {
//        return offeringService.getReview(reviewId);
//    }
//
//    @PutMapping
//    void updateOffering(ReviewUpdateRequest reviewUpdateRequest) {
//        offeringService.updateReview();
//    }
//
//    @DeleteMapping("/{reviewId}")
//    ResponseEntity<Void> deleteOffering(@PathVariable UUID offeringId) {
//        offeringService.deleteOffering(offeringId);
//        return ResponseEntity.noContent().build();
//    }

}
