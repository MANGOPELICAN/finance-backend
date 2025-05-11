package com.example.finance.controller;

import com.example.finance.model.Client;
import com.example.finance.service.ClientService;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/clients")
public class ClientController {

    private final ClientService svc;

    public ClientController(ClientService svc) {
        this.svc = svc;
    }

    @PostMapping
    public Client create(@RequestBody Client client) {
        // now pass name + password instead of name + email
        return svc.createClient(client.getName(), client.getPassword());
    }

    @GetMapping
    public List<Client> list() {
        return svc.listAll();
    }
}
