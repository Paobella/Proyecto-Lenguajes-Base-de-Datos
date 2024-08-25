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
import java.sql.Types;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlOutParameter;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Service
public class UsuarioServiceImpl implements UsuarioService {

    @Autowired
    private UsuarioDao usuarioDao;

    @Autowired
    private JdbcTemplate jdbcTemplate;

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
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("GET_USUARIO_POR_USERNAME_Y_PASSWORD");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("usern", username);
        inParams.put("pass", password);

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
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate);
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("GET_USUARIO_POR_USERNAME_O_CORREO");

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("usern", username);
        inParams.put("corre_us", correo);

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

/////    
   @Override
@Transactional(readOnly = true)
public boolean existeUsuarioPorUsernameOCorreo(String username, String correo) {
    System.out.println(username);
    System.out.println(correo);

    SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
        .withProcedureName("EXISTE_USUARIO_POR_USERNAME_O_CORREO")
        .declareParameters(
            new SqlParameter("usern", Types.VARCHAR),  // Declarar el parámetro de entrada 'usern'
            new SqlParameter("corre", Types.VARCHAR),  // Declarar el parámetro de entrada 'corre'
            new SqlOutParameter("existe", Types.NUMERIC)  // Declarar el parámetro de salida 'existe'
        );

    SqlParameterSource consulta = new MapSqlParameterSource()
        .addValue("usern", username)
        .addValue("corre", correo);

    Map<String, Object> outParams = simpleJdbcCall.execute(consulta);

    Number existe = (Number) outParams.get("existe");

    if (existe != null) {
        System.out.println(existe.intValue());
        return existe.intValue() == 1;
    } else {
        System.out.println("No se pudo obtener el valor de 'existe'.");
        return false;
    }
}

/////

    @Override
    @Transactional
    public void crear_user(Usuario usuario) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("CREAR_USER")
                .declareParameters(
                        new SqlParameter("nomb", Types.VARCHAR),
                        new SqlParameter("apell", Types.VARCHAR),
                        new SqlParameter("corre", Types.VARCHAR),
                        new SqlParameter("direc", Types.VARCHAR),
                        new SqlParameter("usern", Types.VARCHAR),
                        new SqlParameter("pass", Types.VARCHAR),
                        new SqlParameter("tarj", Types.VARCHAR),
                        new SqlParameter("pinn", Types.VARCHAR),
                        new SqlParameter("fech", Types.DATE),
                        new SqlParameter("activ", Types.NUMERIC),
                        new SqlParameter("rol_id", Types.NUMERIC)
                );

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("nomb", usuario.getNombre());
        inParams.put("apell", usuario.getApellidos());
        inParams.put("corre", usuario.getCorreo());
        inParams.put("direc", usuario.getDireccion());
        inParams.put("usern", usuario.getUsername());
        inParams.put("pass", usuario.getPassword());
        inParams.put("tarj", usuario.getTarjeta());
        inParams.put("pinn", usuario.getPin());
        inParams.put("fech", usuario.getFecha());
        inParams.put("activ", usuario.isActivo() ? 1 : 0);
        inParams.put("rol_id", usuario.getRol());

        simpleJdbcCall.execute(inParams);
    }

    @Override
    @Transactional
    public void actuzalizar_user(Usuario usuario) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("ACTUALIZAR_USER")
                .declareParameters(
                        new SqlParameter("usu_id", Types.NUMERIC),
                        new SqlParameter("nomb", Types.VARCHAR),
                        new SqlParameter("apell", Types.VARCHAR),
                        new SqlParameter("corre", Types.VARCHAR),
                        new SqlParameter("direc", Types.VARCHAR),
                        new SqlParameter("usern", Types.VARCHAR),
                        new SqlParameter("pass", Types.VARCHAR),
                        new SqlParameter("tarj", Types.VARCHAR),
                        new SqlParameter("pinn", Types.VARCHAR),
                        new SqlParameter("fech", Types.DATE),
                        new SqlParameter("activ", Types.NUMERIC),
                        new SqlParameter("rol_id", Types.NUMERIC)
                );

        Map<String, Object> inParams = new HashMap<>();
        inParams.put("usu_id", usuario.getIdUsuario());
        inParams.put("nomb", usuario.getNombre());
        inParams.put("apell", usuario.getApellidos());
        inParams.put("corre", usuario.getCorreo());
        inParams.put("direc", usuario.getDireccion());
        inParams.put("usern", usuario.getUsername());
        inParams.put("pass", usuario.getPassword());
        inParams.put("tarj", usuario.getTarjeta());
        inParams.put("pinn", usuario.getPin());
        inParams.put("fech", usuario.getFecha());
        inParams.put("activ", usuario.isActivo() ? 1 : 0);
        inParams.put("rol_id", usuario.getRol());

        simpleJdbcCall.execute(inParams);

    }

    @Override
    @Transactional
    public void delete(Usuario usuario) {
        usuarioDao.delete(usuario);
    }

}
