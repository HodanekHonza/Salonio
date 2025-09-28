package com.salonio.modules.business.infrastructure.persistence.review;

import com.salonio.modules.business.api.dto.review.ReviewResponse;
import com.salonio.modules.business.domain.Review;

public final class ReviewMapper {

    private ReviewMapper() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static ReviewResponse toResponse(final Review review) {
        if (review == null) {
            return null;
        }
        return new ReviewResponse(
                review.getId(),
                review.getText(),
                review.getClientId(),
                review.getBusinessId(),
                review.getRating()
        );
    }

}
