
package com.PlushiePro.PlushiePro.controller;
import com.PlushiePro.PlushiePro.domain.Venta;
import com.PlushiePro.PlushiePro.service.VentaService;
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
@RequestMapping("/venta")
public class VentaController {
    
    @Autowired
    private VentaService ventaService;

    @GetMapping("/listado")
    public String inicio(Model model) {
        var ventas = ventaService.getVentas();
        model.addAttribute("ventas", ventas);
        model.addAttribute("totalVentas", ventas.size());
        return "/venta/listado";
    }
}
    