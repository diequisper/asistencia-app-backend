package com.pi.asistencia_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.asistencia_app.models.AsistenciaLog;

public interface IAsistenciaLogRepository extends JpaRepository<AsistenciaLog, Long>{
    
}