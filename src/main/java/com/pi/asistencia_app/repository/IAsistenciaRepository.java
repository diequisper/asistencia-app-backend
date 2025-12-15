package com.pi.asistencia_app.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.pi.asistencia_app.models.Asistencia;

public interface IAsistenciaRepository extends JpaRepository<Asistencia, Long>{
}
