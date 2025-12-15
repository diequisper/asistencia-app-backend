package com.pi.asistencia_app.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.asistencia_app.dto.JustificacionRequest;
import com.pi.asistencia_app.dto.JustificacionResponse;
import com.pi.asistencia_app.service.IJustificacionService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/justificacion")
public class JustificacionController {

    private final IJustificacionService iJustificacionService;

    @PostMapping("/justificaciones_solicitud")
    public ResponseEntity<JustificacionResponse> registrarJustificacion(@Valid @RequestBody JustificacionRequest jr){
        System.out.println("BUG IDENTIFIER " + jr.getUsuario() + jr.getEstado() + jr.getTipo() + jr.getFecha());
        return ResponseEntity.ok(iJustificacionService.save(jr));
    }

}