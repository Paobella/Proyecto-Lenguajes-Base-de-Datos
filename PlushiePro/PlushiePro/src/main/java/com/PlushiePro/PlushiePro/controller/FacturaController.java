/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PlushiePro.PlushiePro.controller;

import com.PlushiePro.PlushiePro.domain.Factura;
import com.PlushiePro.PlushiePro.service.FacturaService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;

@Controller
@Slf4j
@RequestMapping("/factura")
public class FacturaController {
    
    @Autowired
    private FacturaService facturaService;
    
    
      @GetMapping("/listado")
    public String listado2(Model model) {
        var facturas = facturaService.getFacturas();
        model.addAttribute("facturas", facturas);
        model.addAttribute("totalFacturas", facturas.size());
        return "/factura/listado";
    }
    
    
}
