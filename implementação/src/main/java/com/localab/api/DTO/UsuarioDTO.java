package com.localab.api.DTO;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.localab.api.Model.Type.UsuarioType;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioDTO {
    private Long id;
    private String rg;
    private String cpf;
    private String nome;
    private String email;
    private Long empresaId;
    private String endereco;
    private String profissao;
    private UsuarioType tipo;
    private List<RendimentoDTO> rendimentos;

   @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate criadoEm;
}
