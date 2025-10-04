package com.salonio.modules.business.infrastructure.persistence.category.service;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface ServiceCategoryRepository extends JpaRepository<ServiceCategoryJpaEntity, UUID> {
}
