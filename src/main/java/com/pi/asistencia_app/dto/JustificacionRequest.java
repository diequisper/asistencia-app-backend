package com.pi.asistencia_app.dto;

import java.time.LocalDate;

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
public class JustificacionRequest {

    private Long usuario;

    private LocalDate fecha;

    private String tipo;

    @Size(max = 200, message = "El campo para motivo no puede exceder los 200 caracteres")
    private String motivo;

    private String estado;
}
