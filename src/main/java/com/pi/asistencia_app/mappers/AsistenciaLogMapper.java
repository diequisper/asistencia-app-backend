package com.pi.asistencia_app.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pi.asistencia_app.dto.AsistenciaLogRequest;
import com.pi.asistencia_app.dto.AsistenciaLogResponse;
import com.pi.asistencia_app.models.AsistenciaLog;
import com.pi.asistencia_app.models.AsistenciaLog.Accion;

@Component
public class AsistenciaLogMapper {
    public AsistenciaLog toEntity(AsistenciaLogRequest dto){
        AsistenciaLog.Accion accion = null;
        
        switch (dto.getAccion().toUpperCase()) {
            case "CREACIÓN":
                accion = Accion.CREACIÓN;
                break;
            case "ACTUALIZACIÓN":
                accion = Accion.ACTUALIZACIÓN;
                break;
            case "ELIMINACIÓN":
                accion = Accion.ELIMINACIÓN;
                break;
            default:
                System.err.println("La accion no es ninguna de las opciones posibles");
                break;
        }
        return AsistenciaLog.builder()
                .accion(accion)
                .resultado(dto.getResultado())
                .build();
    }

    public AsistenciaLogResponse toDto(AsistenciaLog entity){
        return AsistenciaLogResponse.builder()
                .id(entity.getId())
                .asistencia(entity.getAsistencia() != null ? entity.getAsistencia().getId() : null)
                .a_usuario_id(entity.getAsistencia() != null ? entity.getAsistencia().getUsuario().getId() : null)
                .entrada(entity.getAsistencia() != null ? entity.getAsistencia().getEntrada() : null)
                .salida(entity.getAsistencia() != null ? entity.getAsistencia().getSalida() : null)
                .usuario(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .username(entity.getUsuario() != null ? entity.getUsuario().getUsername() : null)
                .password(entity.getUsuario() != null ? entity.getUsuario().getPassword() : null)
                .rol(entity.getUsuario() != null ? entity.getUsuario().getRol().toString() : null)
                .activo(entity.getUsuario() != null ? entity.getUsuario().getActivo() : null)
                .accion(entity.getAccion().toString())
                .resultado(entity.getResultado())
                .fecha(entity.getFecha() )
                .build();
    }

    public List<AsistenciaLogResponse> toDtoList(List<AsistenciaLog> entities){
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    public List<AsistenciaLog> toEntityList(List<AsistenciaLogRequest> dtos){
        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }
}
