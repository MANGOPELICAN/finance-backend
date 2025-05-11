package com.example.finance.service;

import com.example.finance.dto.CompanyCreateDTO;
import com.example.finance.dto.CompanyUpdateDTO;
import com.example.finance.mapper.CompanyMapper;
import com.example.finance.model.Company;
import com.example.finance.repository.CompanyRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import static org.springframework.http.HttpStatus.*;

@Service
@RequiredArgsConstructor
public class CompanyService {

    private final CompanyRepository repo;
    private final CompanyMapper     mapper;
    private final PasswordEncoder   encoder;

    /* ---------- CREATE ---------- */
    public Company create(CompanyCreateDTO dto) {
        if (repo.existsByNameIgnoreCase(dto.name())) {
            throw new ResponseStatusException(CONFLICT,
                    "Company '%s' already exists".formatted(dto.name()));
        }
        Company entity = mapper.toEntity(dto);
        entity.setPassword(encoder.encode(dto.rawPassword()));
        return repo.save(entity);
    }

    /* ---------- READ ---------- */
    public Company one(Long id) {
        return repo.findById(id)
                   .orElseThrow(() -> new ResponseStatusException(
                           NOT_FOUND, "Company %d not found".formatted(id)));
    }

    public Page<Company> list(Pageable pageable) {
        return repo.findAll(pageable);
    }

    /* ---------- UPDATE ---------- */
    public Company update(Long id, CompanyUpdateDTO dto) {
        Company entity = one(id);
        mapper.updateEntity(dto, entity);           // merge name
        if (dto.rawPassword() != null && !dto.rawPassword().isBlank()) {
            entity.setPassword(encoder.encode(dto.rawPassword()));
        }
        return repo.save(entity);
    }

    /* ---------- DELETE ---------- */
    public void delete(Long id) {
        if (!repo.existsById(id)) {
            throw new ResponseStatusException(NOT_FOUND,
                    "Company %d not found".formatted(id));
        }
        repo.deleteById(id);
    }
}