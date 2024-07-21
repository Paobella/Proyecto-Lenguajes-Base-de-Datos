/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PlushiePro.PlushiePro.dao;

import org.springframework.data.jpa.repository.JpaRepository;
import com.PlushiePro.PlushiePro.domain.Usuario;

public interface UsuarioDao extends JpaRepository <Usuario, Long> {
    Usuario findByUsername(String username);
    
    Usuario findByUsernameAndPassword(String username, String Password);
    
    Usuario findByUsernameOrCorreo(String username, String Correo);
    
    boolean existsByUsernameOrCorreo(String username, String Correo);
    
}
