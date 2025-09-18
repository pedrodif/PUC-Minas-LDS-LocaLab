package com.localab.api.Interface;

import org.mapstruct.Mapper;
import org.mapstruct.MappingTarget;
import org.mapstruct.NullValuePropertyMappingStrategy;

import com.localab.api.DTO.UsuarioDTO;
import com.localab.api.Model.Entity.Usuario;

@Mapper(componentModel = "spring", nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
public interface UsuarioMapper {
    UsuarioDTO toDTO(Usuario usuario);
    void updateUsuario(Usuario source, @MappingTarget Usuario target);
}