package com.localab.api.DTO;

import java.time.LocalDate;

import com.localab.api.Model.Type.FaseAprovacao;
import com.localab.api.Model.Type.Propriedade;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContratoRequestDTO {
    
    @NotNull
    private Double valorTotal;
    
    @NotNull
    private LocalDate dataInicial;
    
    @NotNull
    private LocalDate dataFinal;
    
    private Long financiadorId;
    
    @NotNull
    private Long locadorId;
    
    @NotNull
    private Long locadoraId;
    
    @NotNull
    private Long automovelId;
    
    @NotNull
    private Propriedade propriedade;
    
    @NotNull
    private FaseAprovacao faseAprovacao;
}
