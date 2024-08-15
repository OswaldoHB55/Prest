package org.esfe.servicios.implementaciones;

import org.esfe.modelos.Prestamo;
import org.esfe.repositorios.IPrestamoRepository;
import org.esfe.servicios.interfaces.IPrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PrestamosService implements IPrestamoService {
    @Autowired
    private IPrestamoRepository prestamoRepository;

    @Override
    public Page<Prestamo> buscarTodosPaginados(Pageable pageable) {
        return prestamoRepository.findAll(pageable);
    }

    @Override
    public List<Prestamo> obtenerTodos() {
        return prestamoRepository.findAll();
    }

    @Override
    public Optional<Prestamo> buscarPorId(Integer id) {
        return prestamoRepository.findById(id);
    }

    @Override
    public Prestamo createOEditOne(Prestamo prestamo) {
        return prestamoRepository.save(prestamo);
    }

    @Override
    public void eliminarPorId(Integer id) {
        prestamoRepository.deleteById(id);
    }
}
