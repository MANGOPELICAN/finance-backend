package com.example.finance.mapper;

import com.example.finance.dto.CompanyCreateDTO;
import com.example.finance.dto.CompanyDTO;
import com.example.finance.model.Company;
import javax.annotation.processing.Generated;
import org.springframework.stereotype.Component;

@Generated(
    value = "org.mapstruct.ap.MappingProcessor",
    date = "2025-05-07T14:10:49-0500",
    comments = "version: 1.6.3, compiler: javac, environment: Java 21.0.7 (BellSoft)"
)
@Component
public class CompanyMapperImpl implements CompanyMapper {

    @Override
    public Company toEntity(CompanyCreateDTO dto) {
        if ( dto == null ) {
            return null;
        }

        Company company = new Company();

        company.setName( dto.name() );

        return company;
    }

    @Override
    public CompanyDTO toDto(Company entity) {
        if ( entity == null ) {
            return null;
        }

        Long id = null;
        String name = null;

        id = entity.getId();
        name = entity.getName();

        CompanyDTO companyDTO = new CompanyDTO( id, name );

        return companyDTO;
    }
}
