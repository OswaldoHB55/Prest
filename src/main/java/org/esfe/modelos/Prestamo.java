package org.esfe.modelos;

import jakarta.persistence.*;

@Entity
@Table(name = "prestamos")
public class Prestamo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "cliente_id")
    private Cliente cliente;

    private int monto;

    private int interes;

    private String plazo;

    private String fecha_inicio;

    private String fecha_final;

    private String estado;
}
