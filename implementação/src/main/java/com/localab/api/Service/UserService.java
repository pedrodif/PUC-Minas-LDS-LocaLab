package com.localab.api.Service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

import com.localab.api.DTO.AuthDTO;
import com.localab.api.DTO.UsuarioDTO;
import com.localab.api.Interface.UsuarioMapper;
import com.localab.api.Model.Entity.Usuario;
import com.localab.api.Repository.UserRepository;

import jakarta.persistence.EntityNotFoundException;
import jakarta.transaction.Transactional;

public class UserService {
    @Autowired
    private UserRepository repository;

    @Autowired
    private UsuarioMapper mapper;

    public List<UsuarioDTO> getAll() {
        return repository.findAll()
                .stream()
                .map(mapper::toDTO)
                .toList();
    }

    public UsuarioDTO getById(Long id) {
        return repository.findById(id)
                .map(mapper::toDTO)
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));
    }

    @Transactional
    public UsuarioDTO create(Usuario usuario) {
        repository.save(usuario);
        return mapper.toDTO(usuario);
    }

    @Transactional
    public UsuarioDTO update(Long id, Usuario usuarioAtualizado) {
        Usuario usuarioRecuperado = repository.findById(id)
                .map(usuario -> {
                    mapper.updateUsuario(usuarioAtualizado, usuario);
                    return usuario;
                })
                .orElseThrow(() -> new EntityNotFoundException("Usuário não encontrado com ID: " + id));

        repository.save(usuarioRecuperado);
        return mapper.toDTO(usuarioRecuperado);
    }

    public String delete(Long id) {
        repository.findById(id)
                .ifPresentOrElse(
                        repository::delete,
                        () -> {
                            throw new EntityNotFoundException("Usuário não encontrado com ID: " + id);
                        });
        return "Usuário deletado com sucesso!";
    }

    public boolean authenticate(AuthDTO authDTO) {
        return repository.findByEmail(authDTO.getEmail())
                .map(usuario -> usuario.getSenha().equals(authDTO.getSenha()))
                .orElse(false);
    }
}
