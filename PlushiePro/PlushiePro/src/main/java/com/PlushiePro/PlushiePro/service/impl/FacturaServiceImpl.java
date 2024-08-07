/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PlushiePro.PlushiePro.service.impl;

import com.PlushiePro.PlushiePro.dao.FacturaDao;
import com.PlushiePro.PlushiePro.domain.Factura;
import com.PlushiePro.PlushiePro.service.FacturaService;
import jakarta.persistence.*;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class FacturaServiceImpl implements FacturaService {

     @PersistenceContext
    private EntityManager entityManager;

    @Override
    @Transactional(readOnly = true)
    public List<Factura> getFacturas() {
        StoredProcedureQuery storedProcedure = entityManager.createStoredProcedureQuery("GET_FACTURAS", Factura.class);
        storedProcedure.execute();
        return storedProcedure.getResultList();
    }
}