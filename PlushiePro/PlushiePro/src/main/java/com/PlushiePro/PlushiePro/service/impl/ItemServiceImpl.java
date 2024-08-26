/*
 * Click nbfs://nbhost/SystemFileSystem/Templates/Licenses/license-default.txt to change this license
 * Click nbfs://nbhost/SystemFileSystem/Templates/Classes/Class.java to edit this template
 */
package com.PlushiePro.PlushiePro.service.impl;

import com.PlushiePro.PlushiePro.dao.FacturaDao;
import com.PlushiePro.PlushiePro.dao.VentaDao;
import com.PlushiePro.PlushiePro.domain.Producto;
import com.PlushiePro.PlushiePro.domain.Usuario;
import com.PlushiePro.PlushiePro.domain.Factura;
import com.PlushiePro.PlushiePro.domain.Item;
import com.PlushiePro.PlushiePro.domain.Venta;
import com.PlushiePro.PlushiePro.service.UsuarioService;
import com.PlushiePro.PlushiePro.service.ItemService;
import java.util.List;
import java.util.Objects;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;
import com.PlushiePro.PlushiePro.dao.ProductoDao;
import static com.PlushiePro.PlushiePro.service.ItemService.listaItems;
import java.util.Map;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.SqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcCall;
import org.springframework.transaction.annotation.Transactional;

@Service
public class ItemServiceImpl implements ItemService {

    @Override
    public List<Item> gets() {
        return listaItems;
    }
    //Se usa en el addCarrito... agrega un elemento

    @Override
    public void save(Item item) {
        boolean existe = false;
        for (Item i : listaItems) {
            //Busca si ya existe el producto en el carrito
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                //Valida si aún puede colocar un item adicional -segun existencias-
                if (i.getCantidad() < item.getExistencias()) {
                    //Incrementa en 1 la cantidad de elementos 
                    i.setCantidad(i.getCantidad() + 1);
                }
                existe = true;
                break;
            }
        }
        if (!existe) {//Si no está el producto en el carritose agrega cantidad =1. 
            item.setCantidad(1);
            listaItems.add(item);
        }
    }
    //Se usa para eliminar un producto del carrito

    @Override
    public void delete(Item item) {
        var posicion = -1;
        var existe = false;
        for (Item i : listaItems) {
            ++posicion;
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                existe = true;
                break;
            }
        }
        if (existe) {
            listaItems.remove(posicion);
        }
    }
    //Se obtiene la información de un producto del carrito... para modificarlo

    @Override
    public Item get(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                return i;
            }
        }
        return null;
    }
    //Se usa en la página para actualizar la cantidad de productos

    @Override
    public void actualiza(Item item) {
        for (Item i : listaItems) {
            if (Objects.equals(i.getIdProducto(), item.getIdProducto())) {
                i.setCantidad(item.getCantidad());
                break;
            }
        }
    }
    @Autowired
    private UsuarioService uuarioService;
    @Autowired
    private FacturaDao facturaDao;
    @Autowired
    private VentaDao ventaDao;
    @Autowired
    private ProductoDao productoDao;
    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public void facturar() {
        System.out.println("Facturando");
        //Se obtiene el usuario autenticado
        String username;
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        if (principal instanceof UserDetails userDetails) {
            username = userDetails.getUsername();
        } else {
            username = principal.toString();
        }
        if (username.isBlank()) {
            return;
        }
        Usuario uuario = uuarioService.getUsuarioPorUsername(username);
        if (uuario == null) {
            return;
        }


        // Configurar SimpleJdbcCall 
        SimpleJdbcCall simpleJdbcCall = new SimpleJdbcCall(jdbcTemplate)
                .withFunctionName("CREAR_FACTURA");
        // Ejecutar la funcion y guardarlo en un objeto para luego obtener el return
              Map<String, Object> retorno = simpleJdbcCall.execute(Map.of("usu_id", uuario.getIdUsuario()));

        // Se guarda el return en un tipo number
        Number idFacturaNumber = (Number) retorno.get("return"); 

        // Convertir el valor a Long
        Long id_factura = idFacturaNumber.longValue();

        double total = 0;
        for (Item i : listaItems) {
            System.out.println("Producto: " + i.getDescripcion() + " Cantidad: " + i.getCantidad() + " Total: " + i.getPrecio() * i.getCantidad());

            SimpleJdbcCall simpleJdbcCall2 = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("INSERTAR_VENTA");

            // Preparar los parámetros de entrada
            SqlParameterSource consulta = new MapSqlParameterSource()
                    .addValue("fac_id", id_factura)
                    .addValue("prod_id", i.getIdProducto())
                    .addValue("prec", i.getPrecio())
                    .addValue("cant", i.getCantidad());

            // Ejecutar el procedimiento almacenado
            simpleJdbcCall2.execute(consulta);

            total += i.getPrecio() * i.getCantidad();
        }
        
        SimpleJdbcCall simpleJdbcCall3 = new SimpleJdbcCall(jdbcTemplate)
                    .withProcedureName("ACTUALIZAR_FACTURA");
            // Preparar los parámetros de entrada
            SqlParameterSource consulta = new MapSqlParameterSource()
                    .addValue("fac_id", id_factura)
                    .addValue("tot", total);
            // Ejecutar el procedimiento almacenado
            simpleJdbcCall3.execute(consulta);

        listaItems.clear();
    }

}
