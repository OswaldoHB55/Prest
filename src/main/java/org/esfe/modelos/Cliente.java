package org.esfe.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Entity
@Table(name = "clientes")
public class Cliente {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El nombre es requerido")
    private String nombre;

    @NotBlank(message = "El email es requerido")
    private String email;

    @NotBlank(message = "El telefono es requerido")
    private String telefono;

    @NotBlank(message = "la direccion es requerida")
    private String direccion;

    @OneToMany(mappedBy = "cliente")
    private List<Prestamo> prestamos;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public @NotBlank(message = "El nombre es requerido") String getNombre() {
        return nombre;
    }

    public void setNombre(@NotBlank(message = "El nombre es requerido") String nombre) {
        this.nombre = nombre;
    }

    public @NotBlank(message = "El email es requerido") String getEmail() {
        return email;
    }

    public void setEmail(@NotBlank(message = "El email es requerido") String email) {
        this.email = email;
    }

    public @NotBlank(message = "El telefono es requerido") String getTelefono() {
        return telefono;
    }

    public void setTelefono(@NotBlank(message = "El telefono es requerido") String telefono) {
        this.telefono = telefono;
    }

    public @NotBlank(message = "la direccion es requerida") String getDireccion() {
        return direccion;
    }

    public void setDireccion(@NotBlank(message = "la direccion es requerida") String direccion) {
        this.direccion = direccion;
    }

    public List<Prestamo> getPrestamos() {
        return prestamos;
    }

    public void setPrestamos(List<Prestamo> prestamos) {
        this.prestamos = prestamos;
    }
}
