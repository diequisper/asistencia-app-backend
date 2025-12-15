package com.pi.asistencia_app.controller;

import java.util.List;

import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.pi.asistencia_app.exception.ValidatedRequestException;
import com.pi.asistencia_app.jwt.JwtUtil;

@RestController
@RequestMapping("/auth")
public class AuthController {
    private JwtUtil jwtUtil;
    private AuthenticationManager authenticationManager;

    public AuthController(JwtUtil jwtUtil, AuthenticationManager authenticationManager){
        this.jwtUtil = jwtUtil;
        this.authenticationManager = authenticationManager;
    }

    @PostMapping("/login")
    public String login(@RequestParam String username, @RequestParam String password){
        try{
            Authentication auth = this.authenticationManager.authenticate(
                new UsernamePasswordAuthenticationToken(username, password)
            );

            List<String> roles = auth.getAuthorities().stream()
                .map(r -> r.getAuthority())
                .toList();
            return jwtUtil.generateToken(username, roles);
        }catch(Exception e){
            System.err.println(e.getMessage());
            throw new ValidatedRequestException("Credenciales Inválidas");
        }
    }
}
