package com.salonio.modules.client.infrastructure.messaging;

import com.salonio.modules.client.application.service.ClientDomainService;
import com.salonio.modules.client.domain.event.CreateNewUserFromClientEvent;
import lombok.AllArgsConstructor;
import org.springframework.context.event.EventListener;
import org.springframework.stereotype.Service;

@AllArgsConstructor
@Service
public class ClientEventListener {

    private final ClientDomainService clientDomainService;

    @EventListener
    public void createClient(CreateNewUserFromClientEvent event) {
        clientDomainService.saveClientAfterUserEvent(event);
    }

}
