package com.salonio.modules.client.application.service;

import com.salonio.modules.client.api.ClientApi;
import com.salonio.modules.client.api.dto.ClientResponse;
import com.salonio.modules.client.api.dto.ClientUpdateRequest;
import com.salonio.modules.client.application.port.out.ClientPersistencePort;
import com.salonio.modules.client.domain.Client;
import com.salonio.modules.client.exception.ClientExceptions;
import com.salonio.modules.client.infrastructure.persistance.ClientMapper;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import java.util.ConcurrentModificationException;
import java.util.UUID;

@AllArgsConstructor
@Service
class ClientService implements ClientApi {

    private final ClientPersistencePort clientPersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(ClientService.class);

    @Override
    public ClientResponse getClient(UUID clientId) {
        final Client client = findClientById(clientId);
        return ClientMapper.toResponse(client);
    }

    @Transactional
    @Override
    public ClientResponse updateClient(UUID clientId, ClientUpdateRequest clientUpdateRequest) {
        final Client existitingClient = findClientById(clientId);

        final Client updatedClient = applyUpdate(clientUpdateRequest, existitingClient);
        final Client savedClient = saveClient(updatedClient);

        return ClientMapper.toResponse(savedClient);
    }

    @Transactional
    @Override
    public void deleteClient(UUID clientId) {
        try {
            clientPersistencePort.deleteById(clientId);
        } catch (EmptyResultDataAccessException e) {
            logger.error("Deleting client failed");
            throw new ClientExceptions.ClientNotFoundException("Client with id " + clientId + " not found");
        }
    }

    private Client saveClient(Client client) {
        try {
            return clientPersistencePort.save(client);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Saving client failed");
            throw new ClientExceptions.ClientConflictException("Saving client conflict");
        }
    }

    private Client findClientById(UUID clientId) {
        return clientPersistencePort.findById(clientId)
                .orElseThrow(() -> {
                    logger.error("Finding client with id {} failed", clientId);
                    return new ClientExceptions.ClientNotFoundException(
                            "Client with id " + clientId + " not found");
                });
    }

    private Client applyUpdate(ClientUpdateRequest clientUpdateRequest, Client existingClient) {
        try {
            return existingClient.updateEntity(clientUpdateRequest);
        } catch (ConcurrentModificationException e) {
            logger.error("Updating client failed");
            throw new ClientExceptions.ClientConflictException(
                    "Client with id was modified concurrently. Please retry.", e);
        }
    }

}
