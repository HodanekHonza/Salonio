package com.salonio.modules.client.application.factory;

import com.salonio.modules.client.domain.Client;
import com.salonio.modules.client.domain.event.CreateNewUserFromClientEvent;

public final class ClientFactory {

    private ClientFactory() {
        throw new UnsupportedOperationException("Utility class");
    }

    public static Client createBaseClientWithUserId(CreateNewUserFromClientEvent dto) {
        return new Client(
                dto.userId()
        );
    }
}
