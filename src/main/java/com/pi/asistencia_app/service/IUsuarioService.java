package com.pi.asistencia_app.service;

import java.util.List;

import com.pi.asistencia_app.commons.ICrudCommonsDto;
import com.pi.asistencia_app.dto.UsuarioRequest;
import com.pi.asistencia_app.dto.UsuarioResponse;

public interface IUsuarioService extends ICrudCommonsDto<UsuarioRequest, UsuarioResponse, Long>{
    List<UsuarioResponse> findAll();
}