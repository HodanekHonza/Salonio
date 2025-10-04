package com.salonio.modules.business.infrastructure.persistence.review;

import com.salonio.modules.business.application.port.review.out.ReviewPersistencePort;
import com.salonio.modules.business.domain.Review;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ReviewRepositoryAdapter implements ReviewPersistencePort {

    private final ReviewRepository reviewRepository;

    private static final Logger logger = LoggerFactory.getLogger(ReviewRepositoryAdapter.class);

    @Override
    public Review save(Review review) {
        logger.debug("Saving review: {}", review);
        final ReviewJpaEntity reviewJpaEntity = ReviewMapper.fromDomain(review);
        final ReviewJpaEntity saved = reviewRepository.save(reviewJpaEntity);
        logger.debug("Review saved with id: {}", saved.getId());
        return ReviewMapper.toDomain(saved);
    }

    @Override
    public Optional<Review> findById(UUID id) {
        logger.debug("Finding review with id: {}", id);
        return reviewRepository.findById(id)
                .map(ReviewMapper::toDomain);
    }

    @Override
    public void deleteById(UUID id) {
        logger.debug("Deleting review with id: {}", id);
        reviewRepository.deleteById(id);
    }

    @Override
    public Page<Review> findReviewsByBusinessId(UUID businessId, Pageable pageable) {
        logger.debug("Finding reviews with business id: {}", businessId);
        return reviewRepository.findByBusinessId(businessId, pageable);
    }

}
