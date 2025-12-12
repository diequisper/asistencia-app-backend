package com.pi.asistencia_app.dto;

import java.time.LocalDateTime;
import jakarta.validation.constraints.PastOrPresent;
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
public class AsistenciaRequest {

    private Long usuario;

    @PastOrPresent(message = "La fecha de entrada no puede estar adelantada al dia de hoy")
    private LocalDateTime entrada;

    @PastOrPresent(message = "La fecha de salida no puede estar adelantada al dia de hoy")
    private LocalDateTime salida;
}
