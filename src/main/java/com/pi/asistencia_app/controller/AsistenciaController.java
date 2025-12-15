package com.pi.asistencia_app.controller;

import java.util.List;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.pi.asistencia_app.dto.AsistenciaRequest;
import com.pi.asistencia_app.dto.AsistenciaResponse;
import com.pi.asistencia_app.service.IAsistenciaService;

import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;  
import lombok.extern.slf4j.Slf4j;

@Slf4j
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/asistencia")
public class AsistenciaController {
    private final IAsistenciaService iAsistenciaService;

    @PostMapping("/checkin")
    public ResponseEntity<AsistenciaResponse> checkIn(@Valid @RequestBody AsistenciaRequest asistenciaRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(iAsistenciaService.save(asistenciaRequest));
    }

    @PostMapping("/checkout")
    public ResponseEntity<AsistenciaResponse> checkOut(@Valid @RequestBody AsistenciaRequest asistenciaRequest){
        return ResponseEntity.status(HttpStatus.CREATED).body(iAsistenciaService.save(asistenciaRequest));
    }

    @PreAuthorize("hasRole('ADMIN')")
    @GetMapping("/historial")
    public ResponseEntity<List<AsistenciaResponse>> historial(){
        return ResponseEntity.status(HttpStatus.CREATED).body(iAsistenciaService.findAll());
    }

}
