package org.esfe.servicios.implementaciones;

import org.esfe.modelos.Prestamo;
import org.esfe.servicios.interfaces.IPrestamoService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;
import java.util.Optional;

public class PrestamosService implements IPrestamoService {
    @Override
    public Page<Prestamo> buscarTodosPaginados(Pageable pageable) {
        return null;
    }

    @Override
    public List<Prestamo> obtenerTodos() {
        return List.of();
    }

    @Override
    public Optional<Prestamo> buscarPorId(Integer id) {
        return Optional.empty();
    }

    @Override
    public Prestamo createOEditOne(Prestamo prestamo) {
        return null;
    }

    @Override
    public void eliminarPorId(Integer prestamo) {

    }
}
