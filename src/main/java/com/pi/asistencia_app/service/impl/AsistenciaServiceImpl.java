package com.pi.asistencia_app.service.impl;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.pi.asistencia_app.dto.AsistenciaRequest;
import com.pi.asistencia_app.dto.AsistenciaResponse;
import com.pi.asistencia_app.exception.ResourceNotFoundException;
import com.pi.asistencia_app.mappers.AsistenciaMapper;
import com.pi.asistencia_app.mappers.OracleExceptionMapper;
import com.pi.asistencia_app.models.Asistencia;
import com.pi.asistencia_app.models.Usuario;
import com.pi.asistencia_app.repository.IUsuarioRepository;
import com.pi.asistencia_app.service.IAsistenciaService;

import jakarta.persistence.EntityManager;
import jakarta.persistence.PersistenceContext;
import jakarta.persistence.PersistenceException;
import jakarta.persistence.StoredProcedureQuery;
import jakarta.transaction.Transactional;
import lombok.RequiredArgsConstructor;

@Service
@Transactional
@RequiredArgsConstructor
public class AsistenciaServiceImpl implements IAsistenciaService {

    @PersistenceContext
    private EntityManager em;

    private final AsistenciaMapper am;
    private final IUsuarioRepository iUsuarioRepository;
    private final OracleExceptionMapper oracleExceptionMapper;

    @Override
    public AsistenciaResponse save(AsistenciaRequest dtoReq) {
        try{
            Asistencia as = am.toEntity(dtoReq);

        if (dtoReq.getUsuario() == null) {
            throw new IllegalArgumentException("Usuario id requerido");
        }
            Long uid = dtoReq.getUsuario();
            Optional<Usuario> aus = iUsuarioRepository.findById(uid);
            if (aus == null) {
                throw new ResourceNotFoundException("no existe el usuario con id: " + uid);
            }

            as.setUsuario(aus.get());

            StoredProcedureQuery sp = em.createNamedStoredProcedureQuery("Asistencia.registrar");
            sp.setParameter("p_usuario", as.getUsuario().getId());
            sp.setParameter("p_entrada", as.getEntrada() != null ? as.getEntrada() : null);
            sp.setParameter("p_salida", as.getSalida() != null ? as.getSalida() : null);
            sp.execute();

            return am.toDto(as);
        } catch (PersistenceException ex) {
            throw oracleExceptionMapper.map(ex);
        }
    }

    @Override
    public AsistenciaResponse update(Long id, AsistenciaRequest dtoReq) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'update'");
    }

    @Override
    public AsistenciaResponse findById(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'findById'");
    }

    @Override
    public AsistenciaResponse delete(Long id) {
        // TODO Auto-generated method stub
        throw new UnsupportedOperationException("Unimplemented method 'delete'");
    }

    @Override
    public List<AsistenciaResponse> findAll() {
        List<Asistencia> asistencias =
        em.createNamedStoredProcedureQuery("Asistencia.listar")
          .getResultList();

        return am.toDtoList(asistencias);
    }

}
