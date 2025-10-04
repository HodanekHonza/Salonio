package com.salonio.modules.business.infrastructure.persistence.review;

import com.salonio.modules.business.domain.Review;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<ReviewJpaEntity, UUID> {

    Page<Review> findByBusinessId(UUID businessId, Pageable pageable);

}
