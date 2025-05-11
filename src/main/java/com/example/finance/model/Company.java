package com.example.finance.model;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Table(name = "companies",
       uniqueConstraints = @UniqueConstraint(
               name = "uk_company_name",
               columnNames = "name"))
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Company {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    /** Company’s display name (must be unique, case-insensitive). */
    @Column(nullable = false)
    private String name;

    /** BCrypt (or placeholder) password hash. */
    @Column(nullable = false)
    private String password;
}
