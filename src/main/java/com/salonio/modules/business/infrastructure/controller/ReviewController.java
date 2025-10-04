package com.salonio.modules.business.infrastructure.controller;

import com.salonio.modules.business.api.dto.review.ReviewCreateRequest;
import com.salonio.modules.business.api.dto.review.ReviewResponse;
import com.salonio.modules.business.api.dto.review.ReviewUpdateRequest;
import com.salonio.modules.business.application.service.review.ReviewService;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@RestController
@RequestMapping("/review")
@AllArgsConstructor
public class ReviewController {

    private final ReviewService reviewService;

    @PostMapping
    ReviewResponse createReview(@Valid @RequestBody ReviewCreateRequest reviewCreateRequest) {
        return reviewService.createReview(reviewCreateRequest);
    }

    @GetMapping("/{reviewId}")
    ReviewResponse getReview(@PathVariable UUID reviewId) {
        return reviewService.getReview(reviewId);
    }

    @PutMapping("/{reviewId}")
    void updateReview(@PathVariable UUID reviewId,
                        ReviewUpdateRequest reviewUpdateRequest) {
        reviewService.updateReview(reviewId, reviewUpdateRequest);
    }

    @DeleteMapping("/{reviewId}")
    ResponseEntity<Void> deleteReview(@PathVariable UUID reviewId) {
        reviewService.deleteReview(reviewId);
        return ResponseEntity.noContent().build();
    }

}
