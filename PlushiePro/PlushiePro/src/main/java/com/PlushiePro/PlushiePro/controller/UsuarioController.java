
package com.PlushiePro.PlushiePro.controller;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import com.PlushiePro.PlushiePro.domain.Usuario;
import com.PlushiePro.PlushiePro.domain.Factura;
import com.PlushiePro.PlushiePro.service.UsuarioService;
import com.PlushiePro.PlushiePro.service.FacturaService;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

@Controller
@RequestMapping("/usuario")
public class UsuarioController {
    
     @Autowired
    private UsuarioService usuarioService;
     
      @Autowired
    private FacturaService facturaService;

   @GetMapping("/listado")
public String listado(Model model) {
    Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
    String username = authentication.getName();
    Usuario usuario = usuarioService.getUsuarioPorUsername(username);
    model.addAttribute("usuario", usuario);
    return "/usuario/listado";
    }


}
