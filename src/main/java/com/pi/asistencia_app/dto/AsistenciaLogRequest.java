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
public class AsistenciaLogRequest {

    private Long asistencia;

    private Long usuario;

    @NotBlank(message = "El campo para acción es requerido")
    private String accion;

    @Size(max = 200, message = "El campo para resultado no puede exceder los 200 caracteres")
    private String resultado;

}
