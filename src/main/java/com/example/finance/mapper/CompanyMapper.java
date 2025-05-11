package com.example.finance.mapper;


import com.example.finance.dto.CompanyCreateDTO;
import com.example.finance.dto.CompanyDTO;
import com.example.finance.model.Company;
import org.mapstruct.Mapper;
import org.mapstruct.Mapping; 

@Mapper(componentModel = "spring")
public interface CompanyMapper {

    @Mapping(target = "id", ignore = true)
    @Mapping(target = "password", source = "rawPassword")
    // inbound mapping (DTO -> Entity)
    Company toEntity(CompanyCreateDTO dto);

    // outbound mapping (Entity -> DTO)
    CompanyDTO toDto(Company entity);
}
