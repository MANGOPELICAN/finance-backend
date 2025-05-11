package com.example.finance.repository;

import com.example.finance.model.Company;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyRepository extends JpaRepository<Company, Long> {

    /** True if a company with the same name (case-insensitive) already exists. */
    boolean existsByNameIgnoreCase(String name);
}
