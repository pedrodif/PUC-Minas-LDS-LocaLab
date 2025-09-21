
package com.localab.api.Model.Entity;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.localab.api.Model.Type.Propriedade;
import com.localab.api.Model.Type.FaseAprovacao;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@Entity
public class Contrato {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    
    @NotNull
    private double valorTotal;

    @NotNull
    private LocalDate dataInicial;

    @NotNull
    private LocalDate dataFinal;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "financiador_id")
    private Empresa financiador;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locador_id")
    private Usuario locador;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "locadora_id")
    private Empresa locadora;
    
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "automovel_id")
    @NotNull
    private Automovel automovel;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private Propriedade propriedade;
    
    @Enumerated(EnumType.STRING)
    @Column(nullable = false)
    private FaseAprovacao faseAprovacao;
    
    @Setter(AccessLevel.NONE)
    @JsonProperty(access = Access.READ_ONLY)
    @Column(nullable = false, updatable = false)
    private LocalDate criadoEm;
    
    @Setter(AccessLevel.NONE)
    @JsonProperty(access = Access.READ_ONLY)
    @Column(nullable = false)
    private LocalDate atualizadoEm;

    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDate.now();
        this.atualizadoEm = LocalDate.now();
    }

    @PreUpdate
    protected void onUpdate() {
        this.atualizadoEm = LocalDate.now();
    }
}