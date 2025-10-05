package com.salonio.modules.client.infrastructure.controller;

import com.salonio.modules.client.api.ClientApi;
import com.salonio.modules.client.api.dto.ClientResponse;
import com.salonio.modules.client.api.dto.ClientUpdateRequest;
import jakarta.validation.Valid;
import lombok.AllArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.UUID;

@AllArgsConstructor
@RestController
@RequestMapping("/client")
public class ClientController {

    private final ClientApi clientApi;

    @GetMapping("/{clientId}")
    ClientResponse getClient(@PathVariable UUID clientId) {
        return clientApi.getClient(clientId);
    }

    @PutMapping("/{clientId}")
    ClientResponse updateClient(@PathVariable UUID clientId,
                                  @Valid @RequestBody ClientUpdateRequest clientUpdateRequest) {
        return clientApi.updateClient(clientId, clientUpdateRequest);
    }

    @DeleteMapping("/{clientId}")
    ResponseEntity<Void> deleteClient(@PathVariable UUID clientId) {
        clientApi.deleteClient(clientId);
        return ResponseEntity.noContent().build();
    }

}
