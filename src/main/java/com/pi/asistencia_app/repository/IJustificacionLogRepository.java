package com.pi.asistencia_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.asistencia_app.models.JustificacionLog;

public interface IJustificacionLogRepository extends JpaRepository<JustificacionLog, Long>{
    
}