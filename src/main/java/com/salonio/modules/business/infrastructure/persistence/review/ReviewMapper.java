package com.salonio.modules.business.infrastructure.persistence.review;

import com.salonio.modules.business.api.dto.review.ReviewResponse;
import com.salonio.modules.business.domain.Business;
import com.salonio.modules.business.domain.Review;
import com.salonio.modules.business.infrastructure.persistence.business.BusinessJpaEntity;

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


    public static Review toDomain(ReviewJpaEntity entity) {
        return new Review(
                entity.getId(),
                entity.getVersion(),
                entity.getText(),
                entity.getClientId(),
                entity.getBusinessId(),
                entity.getRating()
        );
    }

    public static ReviewJpaEntity fromDomain(Review review) {
        return new ReviewJpaEntity(
                review.getId(),
                review.getVersion(),
                review.getText(),
                review.getClientId(),
                review.getBusinessId(),
                review.getRating()
        );
    }

}
