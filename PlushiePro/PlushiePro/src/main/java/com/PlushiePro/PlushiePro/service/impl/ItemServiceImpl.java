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
        Factura factura = new Factura(uuario.getIdUsuario());
        factura = facturaDao.save(factura);
        double total = 0;
        for (Item i : listaItems) {
            System.out.println("Producto: " + i.getDescripcion() + " Cantidad: " + i.getCantidad() + " Total: " + i.getPrecio() * i.getCantidad());
            Venta venta = new Venta(factura.getIdFactura(),
                    i.getIdProducto(), i.getPrecio(), i.getCantidad());
            ventaDao.save(venta);
            Producto producto = productoDao.getReferenceById(i.getIdProducto());
            producto.setExistencias(producto.getExistencias()
                    - i.getCantidad());
            productoDao.save(producto);
            total += i.getPrecio() * i.getCantidad();
        }
        factura.setTotal(total);
        facturaDao.save(factura);
        listaItems.clear();
    }

}
