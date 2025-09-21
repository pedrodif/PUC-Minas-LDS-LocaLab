package com.localab.api.Interface;

import org.mapstruct.Mapper;
import org.mapstruct.factory.Mappers;

import com.localab.api.DTO.AutomovelDTO;
import com.localab.api.Model.Entity.Automovel;

@Mapper(componentModel = "spring")
public interface AutomovelMapper {
    AutomovelMapper INSTANCE = Mappers.getMapper(AutomovelMapper.class);
    
    AutomovelDTO toDTO(Automovel automovel);
    
    Automovel toEntity(AutomovelDTO automovelDTO);
}
