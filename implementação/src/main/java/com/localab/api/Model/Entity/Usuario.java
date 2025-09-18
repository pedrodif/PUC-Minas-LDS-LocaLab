package com.localab.api.Model.Entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;
import com.localab.api.Model.Type.UsuarioType;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.OneToMany;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private String nome;

    @NotNull
    private String endereco;

    @NotNull
    private String profissao;

    @NotNull
    private String email;

    @NotNull
    @JsonProperty(access = Access.WRITE_ONLY)
    private String senha;

    @NotNull
    private String rg;

    @NotNull
    private String cpf;

    @NotNull
    private UsuarioType tipo;

    @ManyToOne(optional = true)
    @JoinColumn(name = "empresa_id")
    private Empresa empresa;

    @OneToMany(mappedBy = "usuario", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Rendimento> rendimentos;

    @Setter(AccessLevel.NONE)
    @JsonProperty(access = Access.READ_ONLY)
    @Column(nullable = false, updatable = false, columnDefinition = "DATE DEFAULT CURRENT_DATE")
    private LocalDate criadoEm;
}
