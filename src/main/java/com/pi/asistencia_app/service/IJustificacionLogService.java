package com.pi.asistencia_app.service;

import java.util.List;

import com.pi.asistencia_app.commons.ICrudCommonsDto;
import com.pi.asistencia_app.dto.JustificacionLogRequest;
import com.pi.asistencia_app.dto.JustificacionLogResponse;

public interface IJustificacionLogService extends ICrudCommonsDto<JustificacionLogRequest, JustificacionLogResponse, Long>{
    List<JustificacionLogResponse> findAll();
}