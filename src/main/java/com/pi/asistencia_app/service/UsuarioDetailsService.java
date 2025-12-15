package com.pi.asistencia_app.service;

import java.util.List;
import java.util.Optional;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import com.pi.asistencia_app.models.Usuario;
import com.pi.asistencia_app.repository.IUsuarioRepository;

@Service
public class UsuarioDetailsService implements UserDetailsService {

    private IUsuarioRepository usuarioRepository;

    public UsuarioDetailsService(IUsuarioRepository usuarioRepository) {
        this.usuarioRepository = usuarioRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<Usuario> usuarioOpt = usuarioRepository.findByUsername(username);
        if (usuarioOpt.isEmpty()) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }
        Usuario usuario = usuarioOpt.get();
        List<SimpleGrantedAuthority> authorities = List.of(new SimpleGrantedAuthority("ROLE_" + usuario.getRol().name()));
        return new User(usuario.getUsername(), usuario.getPassword(), usuario.getActivo(), true, true, true, authorities);
    }
}