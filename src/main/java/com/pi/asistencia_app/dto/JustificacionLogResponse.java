package com.pi.asistencia_app.dto;

import java.time.LocalDate;
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
public class JustificacionLogResponse {
    private Long id;

    //referencia a justificacion
    private Long justificacion;
    private Long j_usuario_id;
    private LocalDate j_fecha;
    private String tipo;
    private String motivo;
    private String estado;

    //referencia a usuario
    private Long usuario;
    private String username;
    private String password;
    private String rol;
    private Boolean activo;

    private String accion;

    private String resultado;

    private LocalDateTime fecha;
}
