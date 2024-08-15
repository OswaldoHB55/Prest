package org.esfe.servicios.interfaces;

import org.esfe.modelos.Prestamo;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public interface IPrestamoService {
    Page<Prestamo> buscarTodosPaginados(Pageable pageable);

    List<Prestamo> obtenerTodos();

    Optional<Prestamo> buscarPorId(Integer id);

    Prestamo createOEditOne(Prestamo prestamo);

    void eliminarPorId(Integer prestamo);
}
