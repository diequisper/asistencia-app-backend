package com.pi.asistencia_app.mappers;

import java.util.List;

import org.springframework.stereotype.Component;
import com.pi.asistencia_app.dto.JustificacionRequest;
import com.pi.asistencia_app.dto.JustificacionResponse;
import com.pi.asistencia_app.models.Justificacion;
import com.pi.asistencia_app.models.Justificacion.Estado;
import com.pi.asistencia_app.models.Justificacion.Tipo;

@Component
public class JustificacionMapper {

    public Justificacion toEntity(JustificacionRequest dto){
        Justificacion.Tipo tipo = null;
        Justificacion.Estado estado = null; 
        
        switch (dto.getTipo().toUpperCase()) {
            case "TARDANZA":
                tipo = Tipo.TARDANZA;
                break;
            case "AUSENCIA":
                tipo = Tipo.AUSENCIA;
                break;
            case "NO LABORAL":
                tipo = Tipo.NO_LABORAL;
                break;
            default:
                System.err.println("El tipo no es ninguna de las opciones posibles");
                break;
        }
        
        switch (dto.getEstado().toUpperCase()) {
            case "APROBADO":
                estado = Estado.APROBADO;
                break;
            case "PENDIENTE":
                estado = Estado.PENDIENTE;
                break;
            case "RECHAZADO":
                estado = Estado.RECHAZADO;
                break;
            default:
                System.err.println("El estado no es ninguna de las opciones posibles");
                break;
        }

        return Justificacion.builder()
                .fecha(dto.getFecha())
                .tipo(tipo)
                .motivo(dto.getMotivo().toString())
                .estado(estado)
                .build();
    }

    public JustificacionResponse toDto(Justificacion entity){
        return JustificacionResponse.builder()
                .id(entity.getId())
                .usuario(entity.getUsuario() != null ? entity.getUsuario().getId() : null)
                .username(entity.getUsuario() != null ? entity.getUsuario().getUsername() : null)
                .password(entity.getUsuario() != null ? entity.getUsuario().getPassword() : null)
                .rol(entity.getUsuario() != null ? entity.getUsuario().getRol().toString() : null)
                .activo(entity.getUsuario() != null ? entity.getUsuario().getActivo() : null)
                .fecha(entity.getFecha())
                .tipo(entity.getTipo().toString())
                .motivo(entity.getMotivo())
                .estado(entity.getEstado().toString())
                .build();
    }

    public List<JustificacionResponse> toDtoList(List<Justificacion> entities){
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    public List<Justificacion> toEntityList(List<JustificacionRequest> dtos){
        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }
}
