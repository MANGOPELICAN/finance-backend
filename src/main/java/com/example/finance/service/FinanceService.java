
package com.example.finance.service;

import com.example.finance.model.Company;
import com.example.finance.repository.CompanyRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional
public class FinanceService {

    private final CompanyRepository companyRepository;

    public FinanceService(CompanyRepository companyRepository) {
        this.companyRepository = companyRepository;
    }

    public Company createCompany(Company c){
        return companyRepository.save(c);
    }
}
