package com.pi.asistencia_app.models;

import java.time.LocalDate;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
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
public class Justificacion {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    private LocalDate fecha;

    public enum Tipo{
        TARDANZA,
        AUSENCIA,
        NO_LABORAL
    }
    @Enumerated(EnumType.STRING)
    private Tipo tipo;

    private String motivo;

    public enum Estado{
        PENDIENTE,
        APROBADO,
        RECHAZADO
    }
    @Enumerated(EnumType.STRING)
    private Estado estado;
}
