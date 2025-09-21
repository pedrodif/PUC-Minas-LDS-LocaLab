package com.localab.api.Repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.localab.api.Model.Entity.Automovel;

@Repository
public interface AutomovelRepository extends JpaRepository<Automovel, Long> {
    
    Optional<Automovel> findByMatricula(String matricula);
    
    Optional<Automovel> findByPlaca(String placa);
    
    List<Automovel> findByMarca(String marca);
    
    List<Automovel> findByModelo(String modelo);
    
    List<Automovel> findByAno(int ano);
    
    @Query("SELECT a FROM Automovel a WHERE a.marca LIKE %:marca%")
    List<Automovel> findByMarcaContaining(@Param("marca") String marca);
    
    @Query("SELECT a FROM Automovel a WHERE a.modelo LIKE %:modelo%")
    List<Automovel> findByModeloContaining(@Param("modelo") String modelo);
}
