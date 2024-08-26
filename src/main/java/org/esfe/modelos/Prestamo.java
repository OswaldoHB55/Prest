package org.esfe.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;

import java.util.Date;
import java.util.LinkedList;
import java.util.List;


@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser un valor positivo")
    private int monto;

    @NotNull(message = "El interés no puede ser nulo")
    @Positive(message = "El interés debe ser un valor positivo")
    private int interes;

    @NotBlank(message = "El plazo es requerido")
    private String plazo;

    //@NotBlank(message = "La fecha de inicio es requerido")
    private Date fecha_inicio;

    //@NotBlank(message = "La fecha final es requerido")
    private Date fecha_final;

    @NotBlank(message = "El codigo es requerido")
    private String codigo;

    @NotBlank(message = "El estado es requerido")
    private String estado;

    @NotNull(message = "El monto restante no puede ser nulo")
    @Positive(message = "El monto restante debe ser un valor positivo")
    private int monto_restante;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Cliente getCliente() {
        return cliente;
    }

    public void setCliente(Cliente cliente) {
        this.cliente = cliente;
    }

    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser un valor positivo")
    public int getMonto() {
        return monto;
    }

    public void setMonto(@NotNull(message = "El monto no puede ser nulo") @Positive(message = "El monto debe ser un valor positivo") int monto) {
        this.monto = monto;
    }

    @NotNull(message = "El interés no puede ser nulo")
    @Positive(message = "El interés debe ser un valor positivo")
    public int getInteres() {
        return interes;
    }

    public void setInteres(@NotNull(message = "El interés no puede ser nulo") @Positive(message = "El interés debe ser un valor positivo") int interes) {
        this.interes = interes;
    }

    public @NotBlank(message = "El plazo es requerido") String getPlazo() {
        return plazo;
    }

    public void setPlazo(@NotBlank(message = "El plazo es requerido") String plazo) {
        this.plazo = plazo;
    }

    public @NotBlank(message = "El codigo es requerido") String getCodigo() {
        return codigo;
    }

    public void setCodigo(@NotBlank(message = "El codigo es requerido") String codigo) {
        this.codigo = codigo;
    }

    public @NotBlank(message = "El estado es requerido") String getEstado() {
        return estado;
    }

    public void setEstado(@NotBlank(message = "El estado es requerido") String estado) {
        this.estado = estado;
    }

    @NotNull(message = "El monto restante no puede ser nulo")
    @Positive(message = "El monto restante debe ser un valor positivo")
    public int getMonto_restante() {
        return monto_restante;
    }

    public void setMonto_restante(@NotNull(message = "El monto restante no puede ser nulo") @Positive(message = "El monto restante debe ser un valor positivo") int monto_restante) {
        this.monto_restante = monto_restante;
    }

    public Date getFecha_inicio() {
        return fecha_inicio;
    }

    public void setFecha_inicio(Date fecha_inicio) {
        this.fecha_inicio = fecha_inicio;
    }

    public Date getFecha_final() {
        return fecha_final;
    }

    public void setFecha_final(Date fecha_final) {
        this.fecha_final = fecha_final;
    }
}
