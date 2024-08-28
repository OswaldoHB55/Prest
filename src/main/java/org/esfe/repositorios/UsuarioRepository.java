package org.esfe.repositorios;

import org.esfe.modelos.Usuario;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UsuarioRepository
   extends JpaRepository<Usuario, Long> {
        Usuario findByEmail(String email);
        Usuario findByToken(String resetToken);
}
