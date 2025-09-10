package com.salonio.offering.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

public interface OfferingRepository extends JpaRepository<Offering, UUID> {
}
