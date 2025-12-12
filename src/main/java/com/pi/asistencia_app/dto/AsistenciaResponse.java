package com.pi.asistencia_app.dto;

import java.time.LocalDateTime;

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
public class AsistenciaResponse {
    private Long id;

    private Long usuario;

    private String username;

    private String password;

    private String rol;

    private Boolean activo;

    private LocalDateTime entrada;

    private LocalDateTime salida;
}
