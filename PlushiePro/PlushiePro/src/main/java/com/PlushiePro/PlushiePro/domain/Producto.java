package com.PlushiePro.PlushiePro.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import lombok.Data;

@Data
@Entity
@Table(name="producto")
public class Producto implements Serializable {
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_producto")
    private Long idProducto;
    private Long idCategoria;
    private String nombre;
    private String descripcion;
    private double precio;
    private int existencias;
    private String rutaImagen;
    @Column(name = "activo")
    private int activo;  // Cambiado de boolean a int

    

    public Producto() {
    }

    public Producto(String nombre, boolean activo) {
        this.nombre = nombre;
        this.activo = activo ? 1 : 0;
    }

    // Metodo de acceso personalizados para manejar la conversion
    public boolean isActivo() {
        return this.activo == 1;
    }

    public void setActivo(boolean activo) {
        this.activo = activo ? 1 : 0;
    }
}
