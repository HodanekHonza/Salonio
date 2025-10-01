package com.salonio.modules.business.application.port.review.out;

import com.salonio.modules.business.domain.Review;
import java.util.Optional;
import java.util.UUID;

public interface ReviewPersistencePort {

    Review save(Review review);

    Optional<Review> findById(UUID id);

    void deleteById(UUID id);

}
