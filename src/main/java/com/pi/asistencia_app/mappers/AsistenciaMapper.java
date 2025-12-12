package com.pi.asistencia_app.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pi.asistencia_app.dto.AsistenciaRequest;
import com.pi.asistencia_app.dto.AsistenciaResponse;
import com.pi.asistencia_app.models.Asistencia;

@Component
public class AsistenciaMapper {
    
    public Asistencia toEntity(AsistenciaRequest dto){
        return Asistencia.builder()
                .entrada(dto.getEntrada())
                .salida(dto.getSalida())
                .build();
    }

    public AsistenciaResponse toDto(Asistencia entity){
        return AsistenciaResponse.builder()
                .id(entity.getId())
                .usuario(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .username(entity.getUsuario() != null ? entity.getUsuario().getUsername() : null)
                .password(entity.getUsuario() != null ? entity.getUsuario().getPassword() : null)
                .rol(entity.getUsuario() != null ? entity.getUsuario().getRol().toString() : null)
                .activo(entity.getUsuario() != null ? entity.getUsuario().getActivo() : null)
                .entrada((entity.getEntrada()))
                .salida(entity.getSalida())
                .build();
    }

    public List<AsistenciaResponse> toDtoList(List<Asistencia> entities){
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    public List<Asistencia> toEntityList(List<AsistenciaRequest> dtoList){
        return dtoList.stream()
                .map(this::toEntity)
                .toList();
    }
}
