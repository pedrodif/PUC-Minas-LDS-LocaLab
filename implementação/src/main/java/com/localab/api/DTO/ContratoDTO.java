package com.localab.api.DTO;

import java.time.LocalDate;

import com.localab.api.Model.Type.FaseAprovacao;
import com.localab.api.Model.Type.Propriedade;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ContratoDTO {
    private Long id;
    
    private Double valorTotal;
    private LocalDate dataInicial;
    private LocalDate dataFinal;
    private Propriedade propriedade;
    private FaseAprovacao faseAprovacao;
    
    
    private EmpresaDTO financiador;
    private UsuarioDTO locador;
    private EmpresaDTO locadora;
    private AutomovelDTO automovel;
    
    private LocalDate criadoEm;
    private LocalDate atualizadoEm;
}
