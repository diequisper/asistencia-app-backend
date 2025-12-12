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
public class AsistenciaLogResponse {
    
    private Long id;

    //referencia a asistencia
    private Long asistencia;
    private Long a_usuario_id;
    private LocalDateTime entrada;
    private LocalDateTime salida;

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
