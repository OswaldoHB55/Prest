package org.esfe.repositorios;

import org.esfe.modelos.Prestamo;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPrestamoRepository extends JpaRepository<Prestamo, Integer> {
}
