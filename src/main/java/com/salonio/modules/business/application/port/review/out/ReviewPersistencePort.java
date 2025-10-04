package com.salonio.modules.business.application.port.review.out;

import com.salonio.modules.business.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import java.util.Optional;
import java.util.UUID;

public interface ReviewPersistencePort {

    Review save(Review review);

    Optional<Review> findById(UUID id);

    void deleteById(UUID id);

    Page<Review> findReviewsByBusinessId(UUID businessId, Pageable pageable);

}
