package com.pi.asistencia_app.models;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "usuario")
@NamedStoredProcedureQueries({
    @NamedStoredProcedureQuery(
        name = "Usuario.registrar",
        procedureName = "PKG_USUARIO.SP_REGUS",
        parameters = {
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_username", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_passwd", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_rol", type = String.class),
            @StoredProcedureParameter(mode = ParameterMode.IN, name = "p_activo", type = Integer.class)
        }
    )

})
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    private String username;

    @Column(name = "passwd")
    private String password;

    public enum Rol{
        ADMIN,
        EMPLEADO
    }
    @Enumerated(EnumType.STRING)
    @Column(name = "rol")
    private Rol rol;

    @Column(name = "activo")
    private Boolean activo;
}
