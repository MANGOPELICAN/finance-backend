package com.example.finance.service;

import com.example.finance.model.Client;
import com.example.finance.repository.ClientRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ClientService {

    private final ClientRepository repo;

    public ClientService(ClientRepository repo) {
        this.repo = repo;
    }

    // changed signature: now takes (name, password)
    public Client createClient(String name, String password) {
        Client client = new Client();
        client.setName(name);
        client.setPassword(password);   // ← use setPassword, not setEmail
        return repo.save(client);
    }

    public List<Client> listAll() {
        return repo.findAll();


        //Add more business methods as needed
    }
}

