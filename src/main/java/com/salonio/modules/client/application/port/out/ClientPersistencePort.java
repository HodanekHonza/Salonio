package com.salonio.modules.client.application.port.out;

import com.salonio.modules.client.domain.Client;
import java.util.Optional;
import java.util.UUID;

public interface ClientPersistencePort {

    Client save(Client client);

    Optional<Client> findById(UUID clientId);

    void deleteById(UUID clientId);

}
