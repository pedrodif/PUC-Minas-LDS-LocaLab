package com.localab.api.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.localab.api.Model.Entity.Contrato;
import com.localab.api.Model.Type.FaseAprovacao;
import com.localab.api.Model.Type.Propriedade;

@Repository
public interface ContratoRepository extends JpaRepository<Contrato, Long> {
    
    List<Contrato> findByFaseAprovacao(FaseAprovacao faseAprovacao);
    
    List<Contrato> findByPropriedade(Propriedade propriedade);
    
    List<Contrato> findByFinanciadorId(Long financiadorId);
    
    List<Contrato> findByLocadorId(Long locadorId);
    
    List<Contrato> findByLocadoraId(Long locadoraId);
    
    @Query("SELECT c FROM Contrato c WHERE c.financiador.id = :financiadorId AND c.faseAprovacao = :faseAprovacao")
    List<Contrato> findByFinanciadorIdAndFaseAprovacao(@Param("financiadorId") Long financiadorId, @Param("faseAprovacao") FaseAprovacao faseAprovacao);
    
    @Query("SELECT c FROM Contrato c WHERE c.locador.id = :locadorId AND c.faseAprovacao = :faseAprovacao")
    List<Contrato> findByLocadorIdAndFaseAprovacao(@Param("locadorId") Long locadorId, @Param("faseAprovacao") FaseAprovacao faseAprovacao);
    
    @Query("SELECT c FROM Contrato c WHERE c.locadora.id = :locadoraId AND c.faseAprovacao = :faseAprovacao")
    List<Contrato> findByLocadoraIdAndFaseAprovacao(@Param("locadoraId") Long locadoraId, @Param("faseAprovacao") FaseAprovacao faseAprovacao);
}
