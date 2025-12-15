package com.pi.asistencia_app.service.impl;

import java.sql.CallableStatement;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Struct;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.jdbc.Work;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import com.pi.asistencia_app.dto.UsuarioRequest;
import com.pi.asistencia_app.dto.UsuarioResponse;
import com.pi.asistencia_app.mappers.OracleExceptionMapper;
import com.pi.asistencia_app.mappers.UsuarioMapper;
import com.pi.asistencia_app.models.Usuario;
import com.pi.asistencia_app.service.IUsuarioService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;
import oracle.jdbc.OracleTypes;

@Service
@Transactional
@RequiredArgsConstructor
public class UsuarioServiceImpl implements IUsuarioService {

    @PersistenceContext
    private EntityManager em;

    private final UsuarioMapper usuarioMapper;
    private final PasswordEncoder passwordEncoder;
    private final OracleExceptionMapper oracleExceptionMapper;

    public Usuario obyid(Long id) {
        final Usuario[] userHolder = new Usuario[1];
        Session session = em.unwrap(Session.class);
        session.doWork(new Work() {
            @Override
            public void execute(Connection connection) throws SQLException {
                try (CallableStatement cs = connection.prepareCall("{call PKG_USUARIO.SP_OBYID(?, ?)}")) {
                    cs.setLong(1, id);
                    cs.registerOutParameter(2, OracleTypes.STRUCT);
                    cs.execute();
                    Struct struct = (Struct) cs.getObject(2);
                    if (struct != null) {
                        Object[] attrs = struct.getAttributes();
                        Long userId = ((Number) attrs[0]).longValue();
                        String username = (String) attrs[1];
                        String password = (String) attrs[2];
                        String rolStr = (String) attrs[3];
                        Usuario.Rol rol = Usuario.Rol.valueOf(rolStr.toUpperCase());
                        Boolean activo = ((Number) attrs[4]).intValue() == 1;
                        userHolder[0] = Usuario.builder()
                            .id(userId)
                            .username(username)
                            .password(password)
                            .rol(rol)
                            .activo(activo)
                            .build();
                    }
                }
            }
        });
        return userHolder[0];
    }

    @Override
    public UsuarioResponse save(UsuarioRequest dtoReq) {
        try {
            Usuario usuario = usuarioMapper.toEntity(dtoReq);
            usuario.setPassword(passwordEncoder.encode(dtoReq.getPassword()));

            StoredProcedureQuery sp = em.createNamedStoredProcedureQuery("Usuario.registrar");
            sp.setParameter("p_username", usuario.getUsername());
            sp.setParameter("p_passwd", usuario.getPassword());
            sp.setParameter("p_rol", usuario.getRol().toString());
            sp.setParameter("p_activo", usuario.getActivo() != null && usuario.getActivo() ? 1 : 0);
            sp.execute();

            return usuarioMapper.toDto(usuario);
        } catch (PersistenceException ex) {
            throw oracleExceptionMapper.map(ex);
        }
    }

    @Override
    public UsuarioResponse update(Long id, UsuarioRequest dtoReq) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public UsuarioResponse findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public UsuarioResponse delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<UsuarioResponse> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }

}
