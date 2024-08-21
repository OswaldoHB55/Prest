package org.esfe.repositorios;

import org.esfe.modelos.Pago;
import org.springframework.data.jpa.repository.JpaRepository;

public interface IPagoRepository extends JpaRepository<Pago, Integer> {
}
