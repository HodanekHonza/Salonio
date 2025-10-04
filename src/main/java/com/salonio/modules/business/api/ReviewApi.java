package com.salonio.modules.business.api;

import com.salonio.modules.business.api.dto.review.ReviewCreateRequest;
import com.salonio.modules.business.api.dto.review.ReviewResponse;
import com.salonio.modules.business.api.dto.review.ReviewUpdateRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.UUID;

public interface ReviewApi {

    ReviewResponse createReview(ReviewCreateRequest reviewCreateRequest);

    ReviewResponse getReview(UUID id);

    Page<ReviewResponse> listReviewsByBusiness(UUID businessId, Pageable pageable);

    ReviewResponse updateReview(UUID id, ReviewUpdateRequest reviewUpdateRequest);

    void deleteReview(UUID id);

}
