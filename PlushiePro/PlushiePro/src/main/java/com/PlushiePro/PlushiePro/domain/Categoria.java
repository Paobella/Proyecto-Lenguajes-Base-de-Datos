
package com.PlushiePro.PlushiePro.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.List;
import lombok.Data;

@Data
@Entity
@Table(name = "categoria")

public class Categoria implements Serializable {
    
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_categoria")
    private Long idCategoria;
    private String nombre;
    private String tipo;
    private boolean activo;
    
     @OneToMany
    @JoinColumn(name = "id_categoria", updatable = false)
    List<Producto> productos;

    public Categoria() {
    }

    public Categoria(String categoria, boolean activo) {
        this.tipo = categoria;
        this.activo = activo;
    }
}