package com.salonio.modules.business.application.service.review;

import com.salonio.modules.business.api.ReviewApi;
import com.salonio.modules.business.api.dto.business.BusinessUpdateRequest;
import com.salonio.modules.business.api.dto.review.ReviewCreateRequest;
import com.salonio.modules.business.api.dto.review.ReviewResponse;
import com.salonio.modules.business.api.dto.review.ReviewUpdateRequest;
import com.salonio.modules.business.infrastructure.persistence.review.ReviewRepository;
import lombok.AllArgsConstructor;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
@AllArgsConstructor
public class ReviewService implements ReviewApi {

    private final ApplicationEventPublisher publisher;

    @Override
    public ReviewResponse createReview(ReviewCreateRequest reviewCreateRequest) {
        return null;
    }

    @Override
    public ReviewResponse getReview(UUID id) {
        return null;
    }

    @Override
    public Page<ReviewResponse> getReviews(UUID businessId, Pageable pageable) {
        return null;
    }

    @Override
    public ReviewResponse updateReview(UUID id, ReviewUpdateRequest reviewUpdateRequest) {
        return null;
    }

    @Override
    public void deleteReview(UUID id) {

    }

}
