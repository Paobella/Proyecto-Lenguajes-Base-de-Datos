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
    @Column(name = "id_categoria")
    private Long idCategoria;
    private String nombre;
    private String tipo;
    @Column(name = "activo")
    private int activo;

   
       public Categoria() {
    }

    public Categoria(String categoria, boolean activo) {
        this.tipo = categoria;
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
