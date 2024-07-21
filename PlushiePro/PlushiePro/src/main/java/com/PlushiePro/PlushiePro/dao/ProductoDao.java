/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PlushiePro.PlushiePro.dao;

import com.PlushiePro.PlushiePro.domain.Producto;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ProductoDao extends JpaRepository<Producto, Long> {
        //Ejemplo de método utilizando Métodos de Query

    
    public List<Producto> findByNombreOrderByNombre(String nombre);

}