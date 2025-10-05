package com.salonio.modules.client.api;

import com.salonio.modules.client.api.dto.ClientResponse;
import com.salonio.modules.client.api.dto.ClientUpdateRequest;

import java.util.UUID;

public interface ClientApi {

//    void createClient();

    ClientResponse getClient(UUID clientId);

    ClientResponse updateClient(UUID clientId, ClientUpdateRequest clientUpdateRequest);

    void deleteClient(UUID clientId);

}
