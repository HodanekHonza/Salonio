package com.salonio.modules.business.infrastructure.persistence.review;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ReviewRepository extends JpaRepository<ReviewJpaEntity, UUID> {

    Page<ReviewJpaEntity> findByBusinessId(UUID businessId, Pageable pageable);

}
