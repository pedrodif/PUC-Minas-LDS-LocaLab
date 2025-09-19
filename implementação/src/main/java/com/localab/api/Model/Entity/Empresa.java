package com.localab.api.Model.Entity;

import java.time.LocalDate;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Empresa {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @NotNull
    private String cnpj;

    @NotNull
    private String razaoSocial;
    
    @OneToMany(mappedBy = "empresa")
    @JsonIgnore
    private List<Usuario> empregados;
    
    @Setter(AccessLevel.NONE)
    @JsonProperty(access = Access.READ_ONLY)
    @Column(nullable = false, updatable = false)
    private LocalDate criadoEm;

    private boolean instituicaoFinanceira;

    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDate.now();
    }
}
