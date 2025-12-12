package com.pi.asistencia_app.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class UsuarioRequest {

    @NotBlank(message = "El campo para el nombre de usuario es requerido")
    @Size(max = 30, message = "El nombre de usuario no debe exceder los 30 caracteres")
    private String username;

    @NotBlank(message = "El campo para la contraseña es requerido")
    @Size(max = 30)
    private String password;

    private String rol;

    private Boolean activo;
}
