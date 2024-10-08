/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PlushiePro.PlushiePro.service.impl;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.PlushiePro.PlushiePro.domain.Usuario;
import com.PlushiePro.PlushiePro.service.UsuarioDetailsService;
import com.PlushiePro.PlushiePro.service.UsuarioService;
import org.springframework.security.core.userdetails.UserDetailsService;

@Service("userDetailsService")
public class UsuarioDetailsServiceImpl implements UserDetailsService {

    @Autowired
    private UsuarioService usuarioService;

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        // Obtener el usuario a través del servicio
        Usuario usuario = usuarioService.getUsuarioPorUsername(username);

        if (usuario == null) {
            throw new UsernameNotFoundException("Usuario no encontrado: " + username);
        }

        // Determinar el rol basado en `id_rol`
        String rol = (usuario.getRol() == 1) ? "ROLE_ADMIN" : "ROLE_USER";
        var roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(rol));

        // Retornar el usuario con su rol
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
    }
}