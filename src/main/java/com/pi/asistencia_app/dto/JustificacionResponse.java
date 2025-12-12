package com.pi.asistencia_app.dto;

import java.time.LocalDate;

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
public class JustificacionResponse {

    private Long id;

    private Long usuario;
    
    private String username;

    private String password;

    private String rol;

    private Boolean activo;

    private LocalDate fecha;

    private String tipo;

    private String motivo;

    private String estado;
}
