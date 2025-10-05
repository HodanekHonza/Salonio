package com.salonio.modules.client.infrastructure.persistance;

import com.salonio.modules.client.application.port.out.ClientPersistencePort;
import com.salonio.modules.client.domain.Client;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import java.util.Optional;
import java.util.UUID;

@AllArgsConstructor
@Service
public class ClientRepositoryAdapter implements ClientPersistencePort {

    private final ClientRepository clientRepository;

    private static final Logger logger = LoggerFactory.getLogger(ClientRepositoryAdapter.class);

    @Override
    public Client save(Client client) {
        logger.debug("Saving client: {}", client);
        final ClientJpaEntity clientJpaEntity = ClientMapper.fromDomain(client);
        final ClientJpaEntity saved = clientRepository.save(clientJpaEntity);
        logger.debug("Client saved with id: {}", saved.getId());
        return ClientMapper.toDomain(saved);
    }

    @Override
    public Optional<Client> findById(UUID clientId) {
        logger.debug("Finding client with id: {}", clientId);
        return clientRepository.findById(clientId)
                .map(ClientMapper::toDomain);
    }

    @Override
    public void deleteById(UUID clientId) {
        logger.debug("Deleting client with id: {}", clientId);
        clientRepository.deleteById(clientId);
    }

}
