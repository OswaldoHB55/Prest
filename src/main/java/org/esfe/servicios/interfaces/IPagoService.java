package org.esfe.servicios.interfaces;

import org.esfe.modelos.Pago;
import org.esfe.modelos.Prestamo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPagoService {
    Page<Pago> buscarTodosPaginados(Pageable pageable);

    List<Pago> obtenerTodos();

    Optional<Pago> buscarPorId(Integer id);

    Pago createOEditOne(Pago pago);

    void eliminarPorId(Integer pago);
}
