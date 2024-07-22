package com.PlushiePro.PlushiePro.domain;

import jakarta.persistence.*;
import java.io.Serializable;
import java.util.Date;
import java.util.List;
import javax.validation.constraints.NotEmpty;
import lombok.Data;

@Entity
@Data
@Table(name ="usuario")
public class Usuario implements Serializable{
    private static final long serialVersionUID = 1L;
    
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id_usuario")
    private Long idUsuario;
    @Column(name = "nombre")
    private String nombre;
    @Column(name = "apellido")
    private String apellidos;
    @Column(name = "correo")
    private String correo;
    @Column(name = "direccion")
    private String direccion;
    @Column(name = "username")
    @NotEmpty
    private String username;
    @Column(name = "password")
    @NotEmpty
    private String password;
    @Column(name = "tarjeta")
    private String tarjeta;
    @Column(name = "pin")
    private String pin;
    @Column(name = "fecha")
    private Date fecha;
    @Column(name = "activo")
    private int activo;  // Cambiado de boolean a int
    @Column(name = "id_rol")
    private int rol;
    
    @OneToMany
    @JoinColumn(name = "id_usuario")
    List<Rol> roles;

    // Mtodo de acceso personalizados para manejar la conversion
    public boolean isActivo() {
        return this.activo == 1;
    }

    public void setActivo(boolean activo) {
        this.activo = activo ? 1 : 0;
    }
}
