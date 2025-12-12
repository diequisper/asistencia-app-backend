package com.pi.asistencia_app.mappers;

import java.util.List;

import org.springframework.stereotype.Component;

import com.pi.asistencia_app.dto.UsuarioRequest;
import com.pi.asistencia_app.dto.UsuarioResponse;
import com.pi.asistencia_app.models.Usuario.Rol;
import com.pi.asistencia_app.models.Usuario;

@Component
public class UsuarioMapper {
    
    public Usuario toEntity(UsuarioRequest dto){
        Usuario.Rol rol = null;

        switch (dto.getRol().toUpperCase()) {
            case "ADMIN":
                rol = Rol.ADMIN;
                break;
            case "EMPLEADO":
                rol = Rol.EMPLEADO;
                break;
            default:
                System.err.println("El rol no es ninguna de las opciones posibles");
                break;
        }

        return Usuario.builder()
                .username(dto.getUsername())
                .password(dto.getPassword())
                .rol(rol)
                .activo(dto.getActivo())
                .build();
    }

    public UsuarioResponse toDto(Usuario entity){
        return UsuarioResponse.builder()
                .id(null)
                .username(entity.getUsername())
                .password(entity.getPassword())
                .rol(entity.getRol().toString())
                .activo(entity.getActivo())
                .build();
    }

    public List<UsuarioResponse> toDtoList(List<Usuario> entities){
        return entities.stream()
                .map(this::toDto)
                .toList();
    }

    public List<Usuario> toEntityList(List<UsuarioRequest> dtos){
        return dtos.stream()
                .map(this::toEntity)
                .toList();
    }
}
