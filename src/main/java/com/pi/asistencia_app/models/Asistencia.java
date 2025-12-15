package com.pi.asistencia_app.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
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
@Table(name = "asistencia")
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "Asistencia.registrar",
        procedureName = "PKG_ASISTENCIA.SP_REGAS",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_usuario", type = Long.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_entrada", type = LocalDateTime.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_salida",  type = LocalDateTime.class)
        }
    ),
    @NamedStoredProcedureQuery(
        name = "Asistencia.listar",
        procedureName = "PKG_ASISTENCIA.SP_LISTARAS",
        resultClasses = Asistencia.class,
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.REF_CURSOR, name = "p_cursor", type = void.class)
        }
    )
})
public class Asistencia {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    private LocalDateTime entrada;

    private LocalDateTime salida;
}
