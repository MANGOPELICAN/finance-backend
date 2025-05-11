package com.example.finance.controller;

import com.example.finance.dto.*;
import com.example.finance.mapper.CompanyMapper;
import com.example.finance.model.Company;
import com.example.finance.service.CompanyService;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Positive;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.*;
import org.springframework.http.*;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.Arrays;

@RestController
@RequestMapping("/api/companies")
@CrossOrigin("*")
@RequiredArgsConstructor
@Validated
public class CompanyController {

    private final CompanyService service;   // business logic
    private final CompanyMapper  mapper;    // DTO ⇆ Entity

    /* ---------- READ (paged list) ---------- */

    /**
     * List companies with optional pagination & multi-column sorting.
     * Examples:
     * <pre>
     *  /api/companies                     → defaults (page 0, size 20, sort name asc)
     *  /api/companies?page=1&size=50
     *  /api/companies?sort=id,desc&sort=name,asc
     * </pre>
     */
    @GetMapping
    public Page<CompanyDTO> list(
            @RequestParam(defaultValue = "0")  int page,
            @RequestParam(defaultValue = "20") int size,
            @RequestParam(defaultValue = "name,asc") String[] sort) {

        Sort springSort = Sort.by(
                Arrays.stream(sort)
                      .map(s -> {
                          String[] parts = s.split(",");
                          return new Sort.Order(
                                  parts.length > 1 && parts[1].equalsIgnoreCase("desc")
                                          ? Sort.Direction.DESC : Sort.Direction.ASC,
                                  parts[0]);
                      })
                      .toList());

        Page<Company> entities = service.list(PageRequest.of(page, size, springSort));
        return entities.map(mapper::toDto);
    }

    /* ---------- READ (single) ---------- */

    @GetMapping("/{id}")
    public CompanyDTO one(@PathVariable @Positive Long id) {
        return mapper.toDto(service.one(id));
    }

    /* ---------- CREATE ---------- */

    @PostMapping
    public ResponseEntity<CompanyDTO> register(@RequestBody @Valid CompanyCreateDTO body) {
        Company created = service.create(body);
        return ResponseEntity.status(HttpStatus.CREATED).body(mapper.toDto(created));
    }

    /* ---------- UPDATE ---------- */

    @PutMapping("/{id}")
    public CompanyDTO update(@PathVariable @Positive Long id,
                             @RequestBody @Valid CompanyUpdateDTO body) {
        return mapper.toDto(service.update(id, body));
    }

    /* ---------- DELETE ---------- */

    @DeleteMapping("/{id}")
    @ResponseStatus(HttpStatus.NO_CONTENT)
    public void delete(@PathVariable @Positive Long id) {
        service.delete(id);
    }
}
