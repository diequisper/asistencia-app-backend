package com.pi.asistencia_app.models;

import org.hibernate.validator.constraints.EAN;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.EnumType;
import jakarta.persistence.Enumerated;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
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
@Table(name = "usuario")
public class Usuario {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Long id;

    @Column(name = "username")
    @NotBlank(message = "El campo para el nombre de usuario es requerido")
    @Size(max = 30, message = "El nombre de usuario no debe exceder los 30 caracteres")
    private String username;

    @Column(name = "passwd")
    @NotBlank(message = "El campo para la contraseña es requerido")
    @Size(max = 30)
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
