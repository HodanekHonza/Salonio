package com.salonio.modules.business.infrastructure.persistence;

import com.salonio.modules.business.domain.Business;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface BusinessRepository extends JpaRepository<Business, UUID> {
}
