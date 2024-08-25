/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PlushiePro.PlushiePro.service.impl;

import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.PlushiePro.PlushiePro.dao.UsuarioDao;
import com.PlushiePro.PlushiePro.domain.Usuario;
import com.PlushiePro.PlushiePro.service.UsuarioService;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioDao usuarioDao;
    
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Autowired
    private SimpleJdbcCall simpleJdbcCall;

    @Override
    @Transactional(readOnly = true)
    public List<Usuario> getUsuarios() {
        return usuarioDao.findAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuario(Usuario usuario) {
        return usuarioDao.findById(usuario.getIdUsuario()).orElse(null);
    }
    

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsername(String username) {
        return usuarioDao.findByUsername(username);
    }
//////
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameYPassword(String username, String password) {
        
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
            .withProcedureName("get_usuario_por_username_y_password");


        Map<String, Object> inParams = new HashMap<>();
        inParams.put("p_username", username);
        inParams.put("p_password", password);

        
        Map<String, Object> outParams = simpleJdbcCall.execute(inParams);

        
        Usuario usuario = new Usuario();
        usuario.setIdUsuario(((Number) outParams.get("usu")).longValue());
        usuario.setNombre((String) outParams.get("nomb"));
        usuario.setApellidos((String) outParams.get("ape"));
        usuario.setCorreo((String) outParams.get("corre"));
        usuario.setDireccion((String) outParams.get("direc"));
        usuario.setTarjeta((String) outParams.get("tarj"));
        usuario.setPin((String) outParams.get("pinn"));
        usuario.setFecha((Date) outParams.get("fech"));
        usuario.setActivo(((Number) outParams.get("acti")).intValue() == 1);
        usuario.setRol(((Number) outParams.get("id_rol")).intValue());

        return usuario;
    }
    
///////
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameOCorreo(String username, String correo) {
        return usuarioDao.findByUsernameOrCorreo(username, correo);
    }

/////    
    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo) {
        return usuarioDao.existsByUsernameOrCorreo(username, correo);
    }
/////
    @Override
    @Transactional
    public void save(Usuario usuario) {
       usuarioDao.save(usuario);
     
    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }
    
}
