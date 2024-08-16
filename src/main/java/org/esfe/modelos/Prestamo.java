package org.esfe.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;

@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @NotBlank(message = "El monto es requerido")
    private int monto;

    @NotBlank(message = "El interes es requerido")
    private int interes;

    @NotBlank(message = "El plazo es requerido")
    private String plazo;

    @NotBlank(message = "La fecha de inicio es requerido")
    private String fecha_inicio;

    @NotBlank(message = "La fecha final es requerido")
    private String fecha_final;

    @NotBlank(message = "El estado es requerido")
    private String estado;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @NotBlank(message = "El monto es requerido")
    public int getMonto() {
        return monto;
    }

    public void setMonto(@NotBlank(message = "El monto es requerido") int monto) {
        this.monto = monto;
    }

    @NotBlank(message = "El interes es requerido")
    public int getInteres() {
        return interes;
    }

    public void setInteres(@NotBlank(message = "El interes es requerido") int interes) {
        this.interes = interes;
    }

    public @NotBlank(message = "El plazo es requerido") String getPlazo() {
        return plazo;
    }

    public void setPlazo(@NotBlank(message = "El plazo es requerido") String plazo) {
        this.plazo = plazo;
    }

    public @NotBlank(message = "La fecha de inicio es requerido") String getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(@NotBlank(message = "La fecha de inicio es requerido") String fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public @NotBlank(message = "La fecha final es requerido") String getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(@NotBlank(message = "La fecha final es requerido") String fecha_final) {
        this.fecha_final = fecha_final;
    }

    public @NotBlank(message = "El estado es requerido") String getEstado() {
        return estado;
    }

    public void setEstado(@NotBlank(message = "El estado es requerido") String estado) {
        this.estado = estado;
    }
}
