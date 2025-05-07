package com.example.finance.controller;

import com.example.finance.dto.CompanyCreateDTO;
import com.example.finance.dto.CompanyDTO;
import com.example.finance.mapper.CompanyMapper;
import com.example.finance.model.Company;
import com.example.finance.repository.CompanyRepository;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin("*")
@RequiredArgsConstructor
public class CompanyController {

    private final CompanyRepository repo;
    private final CompanyMapper mapper;
    private final PasswordEncoder encoder;

    /* ---------- READ ---------- */

    @GetMapping
    public List<CompanyDTO> list() {
        return repo.findAll()
                   .stream()
                   .map(mapper::toDto)
                   .toList();
    }

    @GetMapping("/{id}")
    public CompanyDTO one(@PathVariable Long id) {
        return mapper.toDto(repo.findById(id).orElseThrow());
    }

    /* ---------- CREATE ---------- */

    @PostMapping
    public CompanyDTO register(@RequestBody @Valid CompanyCreateDTO body) {
        Company entity = mapper.toEntity(body);
        entity.setPassword(encoder.encode(body.rawPassword()));
        return mapper.toDto(repo.save(entity));
    }
}
