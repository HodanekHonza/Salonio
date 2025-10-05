package com.salonio.modules.client.infrastructure.persistance;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

interface ClientRepository extends JpaRepository<ClientJpaEntity, UUID> {
}
