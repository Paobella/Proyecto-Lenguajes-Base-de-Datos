/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PlushiePro.PlushiePro.service.impl;

import com.PlushiePro.PlushiePro.dao.ProductoDao;
import com.PlushiePro.PlushiePro.domain.Producto;
import com.PlushiePro.PlushiePro.service.ProductoService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ProductoServiceImpl implements ProductoService {

     @Autowired
    private ProductoDao productoDao;

    @Override
    @Transactional(readOnly = true)
    public List<Producto> getProductos(boolean activo) {
        var lista = productoDao.findAll();
        if (activo) {
            lista.removeIf(e -> !e.isActivo());
        }
        return lista;
    }
    
    
    @Override
    @Transactional(readOnly = true)
    public Producto getProducto(Producto producto) {
        return productoDao.findById(producto.getIdProducto()).orElse(null);
    }

    @Override
    @Transactional
    public void save(Producto producto) {
        productoDao.save(producto);
    }

    @Override
    @Transactional
    public void delete(Producto producto) {
        productoDao.delete(producto);
    }
    
    // Lista de productos con precio entre ordendados por descripción ConsultaAmpliada
    @Override
    @Transactional(readOnly = true)
    public List<Producto> findByNombreOrderByNombre(String nombre) {

        List<Producto> list = productoDao.findAll();

        list = list.stream().filter(prod -> prod.getNombre().toUpperCase().equals(nombre.toUpperCase())).toList();
  
        return list;
        
    }
    

}