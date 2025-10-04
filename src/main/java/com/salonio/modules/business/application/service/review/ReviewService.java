package com.salonio.modules.business.application.service.review;

import com.salonio.modules.business.api.ReviewApi;
import com.salonio.modules.business.api.dto.review.ReviewCreateRequest;
import com.salonio.modules.business.api.dto.review.ReviewResponse;
import com.salonio.modules.business.api.dto.review.ReviewUpdateRequest;
import com.salonio.modules.business.application.factory.review.ReviewFactory;
import com.salonio.modules.business.application.port.review.out.ReviewPersistencePort;
import com.salonio.modules.business.domain.Review;
import com.salonio.modules.business.exception.review.ReviewExceptions;
import com.salonio.modules.business.exception.service.ServiceExceptions;
import com.salonio.modules.business.infrastructure.persistence.review.ReviewMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.ConcurrentModificationException;
import java.util.UUID;

@Service
@AllArgsConstructor
public class ReviewService implements ReviewApi {

    private final ReviewPersistencePort reviewPersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(ReviewService.class);

    @Override
    public ReviewResponse createReview(ReviewCreateRequest reviewCreateRequest) {
        final var newReview = ReviewFactory.createReview(reviewCreateRequest);
        final var savedReview = saveReview(newReview);
        return ReviewMapper.toResponse(savedReview);
    }

    @Override
    public ReviewResponse getReview(UUID businessId, UUID id) {
        final Review review = findReviewById(id);
        return ReviewMapper.toResponse(review);
    }

    @Override
    public Page<ReviewResponse> listReviewsByBusiness(UUID businessId, Pageable pageable) {
        final Page<Review> foundReviews = findReviewsByBusinessId(businessId, pageable);
        return foundReviews.map(ReviewMapper::toResponse);
    }

    @Override
    public ReviewResponse updateReview(UUID id, ReviewUpdateRequest reviewUpdateRequest) {
        final Review existingReview = findReviewById(id);

        final Review updatedReview = applyUpdate(reviewUpdateRequest, existingReview);

        return ReviewMapper.toResponse(updatedReview);
    }

    @Override
    public void deleteReview(UUID id) {
        try {
            reviewPersistencePort.deleteById(id);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Deleting review failed");
            throw new ReviewExceptions.ReviewNotFoundException("Review with id " + id + " not found");
        }
    }


    private Review saveReview(Review review) {
        try {
            return reviewPersistencePort.save(review);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Saving review failed");
            throw new ReviewExceptions.ReviewConflictException("Saving review conflict");
        }
    }

    private Review findReviewById(UUID reviewId) {
        return reviewPersistencePort.findById(reviewId)
                .orElseThrow(() -> {
                    logger.error("Finding review with id {} failed", reviewId);
                    return new ReviewExceptions.ReviewNotFoundException(
                            "Review with id " + reviewId + " not found");
                });
    }

    private Page<Review> findReviewsByBusinessId(UUID businessId, Pageable pageable) {
        try {
            return reviewPersistencePort.findReviewsByBusinessId(businessId, pageable);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Listing reviews failed");
            throw new ServiceExceptions.ServiceNotFoundException("Reviews with businessId: " + businessId + "not found");
        }
    }

    private Review applyUpdate(ReviewUpdateRequest reviewUpdateRequest, Review existingReview) {
        try {
            return existingReview.updateEntity(reviewUpdateRequest);
        } catch (ConcurrentModificationException e) {
            logger.error("Updating review failed");
            throw new ReviewExceptions.ReviewConflictException(
                    "Review with id was modified concurrently. Please retry.", e);
        }
    }

}
