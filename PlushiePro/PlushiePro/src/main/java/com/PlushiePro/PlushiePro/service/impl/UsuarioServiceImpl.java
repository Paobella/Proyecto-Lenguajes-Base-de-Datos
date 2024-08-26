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
import java.util.HashMap;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.SqlParameter;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;

@Service
public class UsuarioServiceImpl implements UsuarioService{

    @Autowired
    private UsuarioDao usuarioDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    

    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsername(String username) {
        // Configurar y ejecutar la función
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("GET_USU_USER")
                .returningResultSet("CDATOS", (rs, rowNum) -> {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getLong("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellido"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setDireccion(rs.getString("direccion"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setTarjeta(rs.getString("tarjeta"));
                    usuario.setPin(rs.getString("pin"));
                    usuario.setFecha(rs.getDate("fecha"));
                    usuario.setActivo(rs.getBoolean("activo"));
                    usuario.setRol(rs.getInt("id_rol"));
                    return usuario;
                });

        // Ejecutar la función y obtener el cursor
        Map<String, Object> out = simpleJdbcCall.execute(Map.of("user_name", username));

        // Retornar el primer usuario encontrado o null
        return ((List<Usuario>) out.get("CDATOS")).stream().findFirst().orElse(null);
        
    }
//////
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameYPassword(String username, String password) {
        // Configurar y ejecutar la función
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("GET_USU_USER_PASS")
                .returningResultSet("CDATOS", (rs, rowNum) -> {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getLong("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellido"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setDireccion(rs.getString("direccion"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setTarjeta(rs.getString("tarjeta"));
                    usuario.setPin(rs.getString("pin"));
                    usuario.setFecha(rs.getDate("fecha"));
                    usuario.setActivo(rs.getBoolean("activo"));
                    usuario.setRol(rs.getInt("id_rol"));
                    return usuario;
                });

        // Ejecutar la función y obtener el cursor
        Map<String, Object> out = simpleJdbcCall.execute(Map.of("user_name", username, "pass", password));

        // Retornar el primer usuario encontrado o null
        return ((List<Usuario>) out.get("CDATOS")).stream().findFirst().orElse(null);
    }
        
///////
    @Override
    @Transactional(readOnly = true)
    public Usuario getUsuarioPorUsernameOCorreo(String username, String correo) {
        
        // Configurar y ejecutar la función
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("GET_USU_USER_CORRE")
                .returningResultSet("CDATOS", (rs, rowNum) -> {
                    Usuario usuario = new Usuario();
                    usuario.setIdUsuario(rs.getLong("id_usuario"));
                    usuario.setNombre(rs.getString("nombre"));
                    usuario.setApellidos(rs.getString("apellido"));
                    usuario.setCorreo(rs.getString("correo"));
                    usuario.setDireccion(rs.getString("direccion"));
                    usuario.setUsername(rs.getString("username"));
                    usuario.setPassword(rs.getString("password"));
                    usuario.setTarjeta(rs.getString("tarjeta"));
                    usuario.setPin(rs.getString("pin"));
                    usuario.setFecha(rs.getDate("fecha"));
                    usuario.setActivo(rs.getBoolean("activo"));
                    usuario.setRol(rs.getInt("id_rol"));
                    return usuario;
                });

        // Ejecutar la función y obtener el cursor
        Map<String, Object> out = simpleJdbcCall.execute(Map.of("user_name", username, "corre", correo));

        // Retornar el primer usuario encontrado o null
        return ((List<Usuario>) out.get("CDATOS")).stream().findFirst().orElse(null);
   
    }

    @Override
    @Transactional(readOnly = true)
    public boolean existeUsuarioPorUsernameOCorreo(String username, String correo) {
        
        // Configurar SimpleJdbcCall 
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("EXISTE_USU");
        // Ejecutar la funcion y guardarlo en un objeto para luego obtener el return
              Map<String, Object> retorno = simpleJdbcCall.execute(Map.of("user_name", username,
                                                                           "correo_user",correo));

        // Se guarda el return en un tipo number
        Number resultado = (Number) retorno.get("return"); 

        // Convertir el valor a Long
        int result = resultado.intValue();
        
        if(result == 0){
            return false;
        }else{
            return true;
        }
        
    }
    
    
    @Override
    @Transactional
    public void crear_user(Usuario usuario) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("CREAR_USUARIO");
                // Preparar los parámetros de entrada
            SqlParameterSource consulta = new MapSqlParameterSource()
                    .addValue("nomb", usuario.getNombre())
                    .addValue("apelli", usuario.getApellidos())
                    .addValue("mail", usuario.getCorreo())
                    .addValue("direc", usuario.getDireccion())
                    .addValue("userna", usuario.getUsername())
                    .addValue("pass", usuario.getPassword())
                    .addValue("tarj", usuario.getTarjeta())
                    .addValue("pin_us", usuario.getPin());

            // Ejecutar el procedimiento almacenado
            simpleJdbcCall.execute(consulta);

    }
/////
    
    @Override
    @Transactional
    public void actualizar_user(Usuario usuario) {
    SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("ACTUALIZAR_USUARIO");
                // Preparar los parámetros de entrada
            SqlParameterSource consulta = new MapSqlParameterSource()
                    .addValue("usuario_id", usuario.getIdUsuario())
                    .addValue("nomb", usuario.getNombre())
                    .addValue("apelli", usuario.getApellidos())
                    .addValue("mail", usuario.getCorreo())
                    .addValue("direc", usuario.getDireccion())
                    .addValue("userna", usuario.getUsername())
                    .addValue("pass", usuario.getPassword())
                    .addValue("tarj", usuario.getTarjeta())
                    .addValue("pin_us", usuario.getPin());

            // Ejecutar el procedimiento almacenado
            simpleJdbcCall.execute(consulta);
    
    
        
    }
    
    
    
    
}
