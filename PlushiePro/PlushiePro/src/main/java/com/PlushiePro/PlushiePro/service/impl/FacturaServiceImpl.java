package com.PlushiePro.PlushiePro.service.impl;

import com.PlushiePro.PlushiePro.dao.FacturaDao;
import com.PlushiePro.PlushiePro.domain.Factura;
import com.PlushiePro.PlushiePro.service.FacturaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacturaServiceImpl implements FacturaService {

    @Autowired
    private FacturaDao facturaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Factura> getFacturas() {
        var lista = facturaDao.findAll();
        return lista;
    }
}