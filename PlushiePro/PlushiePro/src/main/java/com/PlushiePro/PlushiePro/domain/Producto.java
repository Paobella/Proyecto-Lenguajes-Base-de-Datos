
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
    private String nombre;
    private String descripcion;
    private double precio;
    private int existencias;
    private String rutaImagen;
    private boolean activo;

    @ManyToOne
    @JoinColumn(name="id_categoria")
    Categoria categoria;


    public Producto() {
    }

    public Producto(String nombre, boolean activo) {
        this.nombre = nombre;
        this.activo = activo;
    }
    
}
