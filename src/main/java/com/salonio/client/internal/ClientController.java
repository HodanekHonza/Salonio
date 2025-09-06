package com.salonio.client.internal;

import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/client")
public class ClientController {

    ClientService clientService;

    ClientController(ClientService clientService) {
        this.clientService = clientService;
    }

    @PostMapping()
    void createClient() {
        clientService.createClient();
    }

    @GetMapping
    void getClient() {
        clientService.getClient();
    }

    @PutMapping()
    void updateClient() {
        clientService.updateClient();
    }

    @DeleteMapping()
    void deleteClient() {
        clientService.deleteClient();
    }

}
