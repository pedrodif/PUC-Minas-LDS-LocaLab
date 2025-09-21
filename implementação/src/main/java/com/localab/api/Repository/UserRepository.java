package com.localab.api.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.localab.api.Model.Entity.Usuario;
import com.localab.api.Model.Type.UsuarioType;

@Repository
public interface UserRepository extends JpaRepository<Usuario, Long> {
    Optional<Usuario> findByEmail(String email);
    List<Usuario> findByTipo(UsuarioType tipo);
}