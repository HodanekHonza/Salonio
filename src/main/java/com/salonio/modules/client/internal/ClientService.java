package com.salonio.modules.client.internal;

import com.salonio.modules.client.ClientApi;
import org.springframework.context.ApplicationEventPublisher;
import org.springframework.stereotype.Service;

@Service
class ClientService implements ClientApi {

    private final ApplicationEventPublisher publisher;
    private final ClientRepository clientRepository;

    ClientService(ApplicationEventPublisher publisher, ClientRepository clientRepository) {
        this.publisher = publisher;
        this.clientRepository = clientRepository;
    }

    @Override
    public void createClient() {

    }

    @Override
    public void getClient() {

    }

    @Override
    public void updateClient() {

    }

    @Override
    public void deleteClient() {

    }

}
