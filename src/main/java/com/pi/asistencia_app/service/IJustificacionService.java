package com.pi.asistencia_app.service;

import java.util.List;

import com.pi.asistencia_app.commons.ICrudCommonsDto;
import com.pi.asistencia_app.dto.JustificacionRequest;
import com.pi.asistencia_app.dto.JustificacionResponse;

public interface IJustificacionService extends ICrudCommonsDto<JustificacionRequest, JustificacionResponse, Long>{
    List<JustificacionResponse> findAll();
}