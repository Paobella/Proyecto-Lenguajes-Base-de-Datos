package com.PlushiePro.PlushiePro.service.impl;

import com.PlushiePro.PlushiePro.domain.Venta;
import com.PlushiePro.PlushiePro.service.VentaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SimpleJdbcCall simpleJdbcCall;


    @Override
    @Transactional(readOnly = true)
    public List<Venta> getVentas() {
        simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("GET_VENTAS")
                .returningResultSet("ventas", BeanPropertyRowMapper.newInstance(Venta.class));
        return simpleJdbcCall.executeFunction(List.class);
    }
}
