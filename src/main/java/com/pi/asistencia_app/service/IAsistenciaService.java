package com.pi.asistencia_app.service;

import java.util.List;

import com.pi.asistencia_app.commons.ICrudCommonsDto;
import com.pi.asistencia_app.dto.AsistenciaRequest;
import com.pi.asistencia_app.dto.AsistenciaResponse;

public interface IAsistenciaService extends ICrudCommonsDto<AsistenciaRequest, AsistenciaResponse, Long>{
    List<AsistenciaResponse> findAll();
}
