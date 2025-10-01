package com.salonio.modules.business.infrastructure.persistence.service;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface ServiceRepository extends JpaRepository<ServiceJpaEntity, UUID> {
}
