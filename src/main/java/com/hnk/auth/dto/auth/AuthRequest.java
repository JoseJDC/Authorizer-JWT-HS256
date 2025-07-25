package com.hnk.auth.dto.auth;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class AuthRequest {
    @NotBlank(message = "Nombre de usuario requerido")
    private String username;
    @NotBlank(message = "Contraseña requerida")
    private String password;
}
