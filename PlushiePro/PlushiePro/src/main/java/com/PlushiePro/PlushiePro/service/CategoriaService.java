/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PlushiePro.PlushiePro.service;

import com.PlushiePro.PlushiePro.domain.Categoria;
import java.util.List;

public interface CategoriaService {
    List<Categoria> getCategorias(boolean activos);
    Categoria getCategoria(Categoria categoria);
}