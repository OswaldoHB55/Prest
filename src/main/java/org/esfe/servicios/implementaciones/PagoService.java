package org.esfe.servicios.implementaciones;

import org.esfe.modelos.Pago;
import org.esfe.modelos.Prestamo;
import org.esfe.repositorios.IPagoRepository;
import org.esfe.repositorios.IPrestamoRepository;
import org.esfe.servicios.interfaces.IPagoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class PagoService implements IPagoService {

    @Autowired
    private IPagoRepository pagoRepository;

    @Override
    public Page<Pago> buscarTodosPaginados(Pageable pageable) {
        return pagoRepository.findAll(pageable);
    }

    @Override
    public List<Pago> obtenerTodos() {
        return pagoRepository.findAll();
    }

    @Override
    public Optional<Pago> buscarPorId(Integer id) {
        return pagoRepository.findById(id);
    }

    @Override
    public Pago createOEditOne(Pago pago) {
        return pagoRepository.save(pago);
    }

    @Override
    public void eliminarPorId(Integer id) {
        pagoRepository.deleteById(id);
    }

}
