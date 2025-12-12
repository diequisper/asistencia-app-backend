package com.pi.asistencia_app.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
@Table(name = "justificacion")
public class AsistenciaLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "asistencia")
    private Asistencia asistencia;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    public enum Accion{
        CREACIÓN,
        ACTUALIZACIÓN,
        ELIMINACIÓN
    }
    @NotBlank(message = "El campo para acción es requerido")
    private Accion accion;

    @Size(max = 200, message = "El campo para resultado no puede exceder los 200 caracteres")
    private String resultado;

    private LocalDateTime fecha;
}
