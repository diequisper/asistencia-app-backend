package com.pi.asistencia_app.service;

import java.util.List;

import com.pi.asistencia_app.commons.ICrudCommonsDto;
import com.pi.asistencia_app.dto.AsistenciaLogRequest;
import com.pi.asistencia_app.dto.AsistenciaLogResponse;

public interface IAsistenciaLogService extends ICrudCommonsDto<AsistenciaLogRequest, AsistenciaLogResponse, Long>{
    List<AsistenciaLogResponse> findAll();
}