package com.localab.api.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.localab.api.Model.Entity.Empresa;

@Repository
public interface EmpresaRepository extends JpaRepository<Empresa, Long> {
    
    Optional<Empresa> findByCnpj(String cnpj);
    
    Optional<Empresa> findByRazaoSocial(String razaoSocial);
    
    List<Empresa> findByInstituicaoFinanceira(boolean instituicaoFinanceira);
    
    @Query("SELECT e FROM Empresa e WHERE e.razaoSocial LIKE %:razaoSocial%")
    List<Empresa> findByRazaoSocialContaining(@Param("razaoSocial") String razaoSocial);
}
