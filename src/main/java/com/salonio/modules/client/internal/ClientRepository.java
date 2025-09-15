package com.salonio.modules.client.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

interface ClientRepository extends JpaRepository<Client, UUID> {
}
