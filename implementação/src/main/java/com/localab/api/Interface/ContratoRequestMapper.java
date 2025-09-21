package com.localab.api.Interface;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.localab.api.DTO.ContratoRequestDTO;
import com.localab.api.Model.Entity.Contrato;

@Mapper(componentModel = "spring")
public interface ContratoRequestMapper {
    ContratoRequestMapper INSTANCE = Mappers.getMapper(ContratoRequestMapper.class);
    
    @Mapping(target = "financiador", ignore = true)
    @Mapping(target = "locador", ignore = true)
    @Mapping(target = "locadora", ignore = true)
    @Mapping(target = "automovel", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    @Mapping(target = "id", ignore = true)
    Contrato toEntity(ContratoRequestDTO contratoRequestDTO);
}
