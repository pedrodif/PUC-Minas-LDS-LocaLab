package com.localab.api.DTO;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AutomovelDTO {
    private Long id;
    private String matricula;
    private String marca;
    private String modelo;
    private String placa;
    private Integer ano;
}
