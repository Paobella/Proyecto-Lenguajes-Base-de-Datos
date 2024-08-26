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

    @Column(name = "nombre")
    private String nombre;

    @Column(name = "tipo")
    private String tipo;

    @Column(name = "activo")
    private int activo; // Esto se mapea a un int en la base de datos

    @OneToMany(mappedBy = "categoria")
    private List<Producto> productos;

    public Categoria() {
    }

    public Categoria(String nombre, String tipo, boolean activo) {
        this.nombre = nombre;
        this.tipo = tipo;
        this.activo = activo ? 1 : 0; // Conversion entre booleano y int
    }

    // Metodo de acceso personalizados para manejar la conversión
    public boolean isActivo() {
        return this.activo == 1;
    }
}