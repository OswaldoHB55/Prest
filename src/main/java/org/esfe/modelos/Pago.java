package org.esfe.modelos;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Positive;


@Entity
@Table(name = "pagos")
public class Pago {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "prestamo_id")
    private Prestamo prestamo;

    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser un valor positivo")
    private int monto_pago;
    @NotBlank(message = "La fecha de pago es requerido")
    private String fecha_pago;
    @NotBlank(message = "El Pago es requerido")
    private String metodo_pago;
    @NotBlank(message = "El Estado es requerido")
    private String estado_pago;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Prestamo getPrestamo() {
        return prestamo;
    }

    public void setPrestamo(Prestamo prestamo) {
        this.prestamo = prestamo;
    }

    @NotNull(message = "El monto no puede ser nulo")
    @Positive(message = "El monto debe ser un valor positivo")
    public int getMonto_pago() {
        return monto_pago;
    }

    public void setMonto_pago(@NotNull(message = "El monto no puede ser nulo") @Positive(message = "El monto debe ser un valor positivo") int monto_pago) {
        this.monto_pago = monto_pago;
    }

    public @NotBlank(message = "La fecha de pago es requerido") String getFecha_pago() {
        return fecha_pago;
    }

    public void setFecha_pago(@NotBlank(message = "La fecha de pago es requerido") String fecha_pago) {
        this.fecha_pago = fecha_pago;
    }

    public @NotBlank(message = "El Pago es requerido") String getMetodo_pago() {
        return metodo_pago;
    }

    public void setMetodo_pago(@NotBlank(message = "El Pago es requerido") String metodo_pago) {
        this.metodo_pago = metodo_pago;
    }

    public @NotBlank(message = "El Estado es requerido") String getEstado_pago() {
        return estado_pago;
    }

    public void setEstado_pago(@NotBlank(message = "El Estado es requerido") String estado_pago) {
        this.estado_pago = estado_pago;
    }
}
