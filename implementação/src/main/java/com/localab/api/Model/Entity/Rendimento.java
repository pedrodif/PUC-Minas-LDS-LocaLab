package com.localab.api.Model.Entity;

import java.time.LocalDate;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonProperty;
import com.fasterxml.jackson.annotation.JsonProperty.Access;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
import jakarta.validation.constraints.NotNull;
import lombok.AccessLevel;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Entity
public class Rendimento {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @NotNull
    private Double valor;
    
    @NotNull
    @JsonFormat(pattern = "yyyy-MM-dd")
    private LocalDate data;

    @ManyToOne(optional = false)
    @JoinColumn(name = "usuario_id")
    @JsonIgnore
    private Usuario usuario;

    @Setter(AccessLevel.NONE)
    @JsonProperty(access = Access.READ_ONLY)
    @Column(nullable = false, updatable = false)
    private LocalDate criadoEm;

    @PrePersist
    protected void onCreate() {
        this.criadoEm = LocalDate.now();
    }
}
