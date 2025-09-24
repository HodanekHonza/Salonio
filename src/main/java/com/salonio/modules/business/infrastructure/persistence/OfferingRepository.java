package com.salonio.modules.business.infrastructure.persistence;

import com.salonio.modules.business.domain.Offering;
import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface OfferingRepository extends JpaRepository<Offering, UUID> {
}
