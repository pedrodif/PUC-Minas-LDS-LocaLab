package com.localab.api.Interface;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.localab.api.DTO.EmpresaDTO;
import com.localab.api.Model.Entity.Empresa;

@Mapper(componentModel = "spring")
public interface EmpresaMapper {
    EmpresaMapper INSTANCE = Mappers.getMapper(EmpresaMapper.class);
    
    @Mapping(target = "empregados", ignore = true)
    EmpresaDTO toDTO(Empresa empresa);
    
    @Mapping(target = "empregados", ignore = true)
    Empresa toEntity(EmpresaDTO empresaDTO);
}
