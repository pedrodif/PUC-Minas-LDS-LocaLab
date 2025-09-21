package com.localab.api.Interface;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.factory.Mappers;

import com.localab.api.DTO.ContratoDTO;
import com.localab.api.Model.Entity.Contrato;

@Mapper(componentModel = "spring")
public interface ContratoMapper {
    ContratoMapper INSTANCE = Mappers.getMapper(ContratoMapper.class);
    
    @Mapping(target = "financiador", ignore = true)
    @Mapping(target = "locador", ignore = true)
    @Mapping(target = "locadora", ignore = true)
    @Mapping(target = "automovel", ignore = true)
    ContratoDTO toDTO(Contrato contrato);
    
    @Mapping(target = "financiador", ignore = true)
    @Mapping(target = "locador", ignore = true)
    @Mapping(target = "locadora", ignore = true)
    @Mapping(target = "automovel", ignore = true)
    @Mapping(target = "criadoEm", ignore = true)
    @Mapping(target = "atualizadoEm", ignore = true)
    Contrato toEntity(ContratoDTO contratoDTO);
}
