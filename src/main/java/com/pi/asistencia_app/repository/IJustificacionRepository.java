package com.pi.asistencia_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.asistencia_app.models.Justificacion;

public interface IJustificacionRepository extends JpaRepository<Justificacion, Long>{
    
}