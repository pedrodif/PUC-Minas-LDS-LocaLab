package com.localab.api.DTO;

import jakarta.validation.constraints.NotNull;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class AuthDTO {
    @NotNull
    private String senha;

    @NotNull
    private String email;
}
