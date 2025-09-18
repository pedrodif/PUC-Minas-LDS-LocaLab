package com.localab.api.DTO;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;

import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class RendimentoDTO {
    private Double valor;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate data;

    @JsonFormat(pattern = "dd/MM/yyyy")
    private LocalDate criadoEm;
}