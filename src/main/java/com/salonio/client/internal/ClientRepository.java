package com.salonio.client.internal;

import org.springframework.data.jpa.repository.JpaRepository;
import java.util.UUID;

interface ClientRepository extends JpaRepository<Client, UUID> {
}
