package com.salonio.modules.business.infrastructure.persistence.category.business;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface BusinessCategoryRepository extends JpaRepository<BusinessCategoryJpaEntity, UUID> {
}
