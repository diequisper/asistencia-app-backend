package com.pi.asistencia_app.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pi.asistencia_app.dto.JustificacionLogRequest;
import com.pi.asistencia_app.dto.JustificacionLogResponse;
import com.pi.asistencia_app.models.JustificacionLog;
import com.pi.asistencia_app.models.JustificacionLog.Accion;

@Component
public class JustificacionLogMapper {
    public JustificacionLog toEntity(JustificacionLogRequest dto){
        JustificacionLog.Accion accion = null;
        
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
        return JustificacionLog.builder()
                .accion(accion)
                .resultado(dto.getResultado())
                .build();
    }

    public JustificacionLogResponse toDto(JustificacionLog entity){
        return JustificacionLogResponse.builder()
                .id(entity.getId())
                .justificacion(entity.getJustificacion() != null ? entity.getJustificacion().getId() : null)
                .j_usuario_id(entity.getJustificacion() != null ? entity.getJustificacion().getUsuario().getId() : null)
                .j_fecha(entity.getJustificacion() != null ? entity.getJustificacion().getFecha() : null)
                .tipo(entity.getJustificacion() != null ? entity.getJustificacion().getTipo().toString() : null)
                .motivo(entity.getJustificacion() != null ? entity.getJustificacion().getMotivo() : null)
                .estado(entity.getJustificacion() != null ? entity.getJustificacion().getEstado().toString() : null)
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

    public List<JustificacionLogResponse> toDtoList(List<JustificacionLog> entities){
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    public List<JustificacionLog> toEntityList(List<JustificacionLogRequest> dtos){
        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }
}
