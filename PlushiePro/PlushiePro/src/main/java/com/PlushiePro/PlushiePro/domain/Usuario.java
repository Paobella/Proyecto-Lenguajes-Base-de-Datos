package com.PlushiePro.PlushiePro.domain;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.Table;
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
    private boolean activo;
    @Column(name = "id_rol")
    private int rol;
    
    @OneToMany
    @JoinColumn(name = "id_usuario")
    List<Rol> roles;
      
}