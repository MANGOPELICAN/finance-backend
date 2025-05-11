package com.example.finance.repository;

import com.example.finance.model.Client;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ClientRepository extends JpaRepository<Client, Long> {
    // You can add custom queries here, e.g.:
    // Optional<Client> findByEmail(String email);
}
