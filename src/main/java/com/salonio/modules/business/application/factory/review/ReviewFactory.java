package com.salonio.modules.business.application.factory.review;

import com.salonio.modules.business.api.dto.review.ReviewCreateRequest;
import com.salonio.modules.business.domain.Review;

public final class ReviewFactory {

    private ReviewFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Review createReview(ReviewCreateRequest reviewCreateRequest) {
        return new Review(
                reviewCreateRequest.textForm(),
                reviewCreateRequest.clientId(),
                reviewCreateRequest.businessId(),
                reviewCreateRequest.rating()
        );
    }

}
