package com.pi.asistencia_app.models;

import java.time.LocalDateTime;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.PrePersist;
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
@Table(name = "log_justificacion")
public class JustificacionLog {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @ManyToOne
    @JoinColumn(name = "justificacion")
    private Justificacion justificacion;

    @ManyToOne
    @JoinColumn(name = "usuario")
    private Usuario usuario;

    public enum Accion{
        CREACIÓN,
        ACTUALIZACIÓN,
        ELIMINACIÓN
    }
    private Accion accion;

    private String resultado;

    private LocalDateTime fecha;

    @PrePersist
    protected void onCreate() {
        this.fecha = LocalDateTime.now();
    }  
}
