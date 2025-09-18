package com.localab.api.DTO;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmpresaDTO {
    private long id;
    private String cnpj;
    private String razaoSocial;
    private boolean instituicaoFinanceira;
    private List<String> empregados;
    
    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate criadoEm;
}
