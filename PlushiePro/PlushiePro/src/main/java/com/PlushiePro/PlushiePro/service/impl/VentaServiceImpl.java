
package com.PlushiePro.PlushiePro.service.impl;
import com.PlushiePro.PlushiePro.dao.VentaDao;
import com.PlushiePro.PlushiePro.domain.Venta;
import com.PlushiePro.PlushiePro.service.VentaService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class VentaServiceImpl implements VentaService {

    @Autowired
    private VentaDao ventaDao;

    @Override
    @Transactional(readOnly = true)
    public List<Venta> getVentas() {
        var lista = ventaDao.findAll();
        return lista;
    }
}