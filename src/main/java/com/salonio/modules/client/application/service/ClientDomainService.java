package com.salonio.modules.client.application.service;

import com.salonio.modules.client.application.factory.ClientFactory;
import com.salonio.modules.client.application.port.out.ClientPersistencePort;
import com.salonio.modules.client.domain.Client;
import com.salonio.modules.client.domain.event.CreateNewUserFromClientEvent;
import com.salonio.modules.client.exception.ClientExceptions;
import lombok.AllArgsConstructor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.dao.OptimisticLockingFailureException;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClientDomainService {

//    private final ClientEventPort clientEventPort;
    private final ClientPersistencePort clientPersistencePort;

    private static final Logger logger = LoggerFactory.getLogger(ClientDomainService.class);

    public void saveClientAfterUserEvent(CreateNewUserFromClientEvent createdNewUserEvent) {
        final Client newClient = ClientFactory.createBaseClientWithUserId(createdNewUserEvent);
        final Client savedClient = saveClient(newClient);
        // TODO send event to notification module so we can notify the user about tne new account they created, change User param from username to Email
    }

    private Client saveClient(Client newClient) {
        try {
            return clientPersistencePort.save(newClient);
        } catch (OptimisticLockingFailureException e) {
            logger.error("Saving client failed");
            throw new ClientExceptions.ClientConflictException("Saving client conflict");
        }
    }

}
