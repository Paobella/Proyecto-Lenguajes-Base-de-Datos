/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PlushiePro.PlushiePro.service.impl;

import com.PlushiePro.PlushiePro.domain.Categoria;
import com.PlushiePro.PlushiePro.service.CategoriaService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;
import java.util.Map;

@Service
public class CategoriaServiceImpl implements CategoriaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall simpleJdbcCall;

    @Autowired
    public CategoriaServiceImpl(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
        this.simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("GET_CATEGORIAS")
                .returningResultSet("p_resultado", new CategoriaRowMapper());
    }

    @Override
    @Transactional(readOnly = true)
    public List<Categoria> getCategorias(boolean activos) {
        Map<String, Object> result = simpleJdbcCall.execute();
        List<Categoria> lista = (List<Categoria>) result.get("p_resultado");

        if (activos) {
            lista.removeIf(e -> !e.isActivo());
        }

        return lista;
    }

    @Override
    @Transactional(readOnly = true)
    public Categoria getCategoria(Categoria categoria) {
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withProcedureName("GET_CATEGORIA_BY_ID")
                .returningResultSet("p_resultado", new CategoriaRowMapper());

        Map<String, Object> result = simpleJdbcCall.execute(Map.of("p_id_categoria", categoria.getIdCategoria()));
        List<Categoria> categorias = (List<Categoria>) result.get("p_resultado");

        return categorias.isEmpty() ? null : categorias.get(0);
    }

    private static class CategoriaRowMapper implements RowMapper<Categoria> {
        @Override
        public Categoria mapRow(ResultSet rs, int rowNum) throws SQLException {
            Categoria categoria = new Categoria();
            categoria.setIdCategoria(rs.getLong("idCategoria"));
            categoria.setNombre(rs.getString("nombre"));
            categoria.setTipo(rs.getString("tipo"));
            categoria.setActivo(rs.getInt("activo"));
            return categoria;
        }
    }
}
