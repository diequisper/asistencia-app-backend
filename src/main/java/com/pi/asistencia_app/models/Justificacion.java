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
import jakarta.persistence.NamedStoredProcedureQueries;
import jakarta.persistence.NamedStoredProcedureQuery;
import jakarta.persistence.ParameterMode;
import jakarta.persistence.StoredProcedureParameter;
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
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "Justificacion.registrar",
        procedureName = "PKG_JUSTIFICACION.SP_REGJUS",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_usuario", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_fecha", type = LocalDate.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_tipo", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_motivo", type = String.class)
        }
    )
})
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
