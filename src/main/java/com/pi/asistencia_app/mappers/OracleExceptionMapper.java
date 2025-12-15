package com.pi.asistencia_app.mappers;

import java.sql.SQLException;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;

import com.pi.asistencia_app.exception.RuntimeCustomException;

import jakarta.persistence.PersistenceException;

@Component
public class OracleExceptionMapper {
    
    private static final Logger log = LoggerFactory.getLogger(OracleExceptionMapper.class);
    
    public RuntimeCustomException map(PersistenceException ex) {

        Throwable cause = ex.getCause();

        while (cause != null) {
            if (cause instanceof SQLException sqlEx) {

                int errorCode = sqlEx.getErrorCode();
                log.error("SQL Error code: {}, message: {}", errorCode, sqlEx.getMessage());

                return switch (errorCode) {
                    case 20001 ->
                        new RuntimeCustomException("Usuario inactivo");
                    case 20002 ->
                        new RuntimeCustomException("Ya existe un registro completo para hoy");
                    default ->
                        new RuntimeCustomException("Error de base de datos");
                };
            }
            cause = cause.getCause();
        }

        return new RuntimeCustomException("Error inesperado");
    }
}
