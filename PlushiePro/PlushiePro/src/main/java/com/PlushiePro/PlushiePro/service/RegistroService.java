/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Interface.java to edit this template
 */
package com.PlushiePro.PlushiePro.service;

import com.PlushiePro.PlushiePro.domain.Usuario;
import jakarta.mail.MessagingException;
import org.springframework.ui.Model;

public interface RegistroService {

    public Model activar(Model model, String usuario, String clave);

    public Model crearUsuario(Model model, Usuario usuario) throws MessagingException;
    
    public void activar(Usuario usuario);
    
    public Model recordarUsuario(Model model, Usuario usuario) throws MessagingException;
}