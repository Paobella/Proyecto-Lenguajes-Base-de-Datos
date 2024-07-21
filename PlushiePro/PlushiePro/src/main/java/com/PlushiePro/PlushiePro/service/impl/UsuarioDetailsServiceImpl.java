/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PlushiePro.PlushiePro.service.impl;

import jakarta.servlet.http.HttpSession;
import java.util.ArrayList;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.PlushiePro.PlushiePro.dao.UsuarioDao;
import com.PlushiePro.PlushiePro.domain.Usuario;
import com.PlushiePro.PlushiePro.service.UsuarioDetailsService;


@Service("userDetailsService")
public class UsuarioDetailsServiceImpl implements UsuarioDetailsService, UserDetailsService {

    @Autowired
    private UsuarioDao usuarioDao;
   

    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario = usuarioDao.findByUsername(username);
        if (usuario == null) {
            throw new UsernameNotFoundException(username);
        }
        String rol = (usuario.getRol() == 1) ? "ROLE_ADMIN" : "ROLE_USER";
        var roles = new ArrayList<GrantedAuthority>();
        roles.add(new SimpleGrantedAuthority(rol));
        return new User(usuario.getUsername(), usuario.getPassword(), roles);
        
    }

}
