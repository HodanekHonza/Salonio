package com.salonio.modules.business.infrastructure.persistence.business;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import java.util.List;
import java.util.UUID;

public interface BusinessRepository extends JpaRepository<BusinessJpaEntity, UUID> {

    Page<BusinessJpaEntity> findByBusinessType(String businessType, Pageable pageable);

    @Query("SELECT b.id FROM BusinessJpaEntity b WHERE b.scheduling = :scheduling")
    List<UUID> findBusinessByScheduling(boolean scheduling);

}
