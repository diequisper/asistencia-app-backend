package com.pi.asistencia_app.service.impl;
import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pi.asistencia_app.dto.JustificacionRequest;
import com.pi.asistencia_app.dto.JustificacionResponse;
import com.pi.asistencia_app.exception.ResourceNotFoundException;
import com.pi.asistencia_app.mappers.JustificacionMapper;
import com.pi.asistencia_app.mappers.OracleExceptionMapper;
import com.pi.asistencia_app.models.Justificacion;
import com.pi.asistencia_app.models.Usuario;
import com.pi.asistencia_app.repository.IUsuarioRepository;
import com.pi.asistencia_app.service.IJustificacionService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class JustificacionServiceImpl implements IJustificacionService {

    @PersistenceContext
    private EntityManager em;

    private final OracleExceptionMapper oracleExceptionMapper;
    private final JustificacionMapper jm;
    private final IUsuarioRepository iUsuarioRepository;

    @Override
    public JustificacionResponse save(JustificacionRequest dtoReq) {
        try {
            Justificacion justificacion = jm.toEntity(dtoReq);

            if (dtoReq.getUsuario() == null) {
                throw new IllegalArgumentException("Usuario id requerido");
            }

            Long uid = dtoReq.getUsuario();
            Optional<Usuario> jus = iUsuarioRepository.findById(uid);
            if (jus == null) {
                throw new ResourceNotFoundException("no existe el usuario con id: " + uid);
            }
            
            justificacion.setUsuario(jus.get());

            StoredProcedureQuery sp = em.createNamedStoredProcedureQuery("Justificacion.registrar");
            sp.setParameter("p_usuario", uid);
            sp.setParameter("p_fecha", justificacion.getFecha());
            sp.setParameter("p_tipo", justificacion.getTipo().toString());
            sp.setParameter("p_motivo", justificacion.getMotivo());
            sp.execute();

            return jm.toDto(justificacion);

        } catch (PersistenceException ex) {
            throw oracleExceptionMapper.map(ex);
        }
    }

    @Override
    public JustificacionResponse update(Long id, JustificacionRequest dtoReq) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public JustificacionResponse findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public JustificacionResponse delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<JustificacionResponse> findAll() {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findAll'");
    }
    
}