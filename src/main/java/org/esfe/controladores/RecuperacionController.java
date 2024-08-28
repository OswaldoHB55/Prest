package org.esfe.controladores;

import org.esfe.modelos.Usuario;
import org.esfe.repositorios.UsuarioRepository;
import org.esfe.security.Email;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class RecuperacionController {


        @Autowired
        private UsuarioRepository usuarioRepository;

        @Autowired
        private Email email;

        @PostMapping("/recoverPassword")
        public String recoverPassword(@RequestParam("email") String email) {
            Usuario usuario = usuarioRepository.findByEmail(email);

            if (usuario != null) {
                String token = generarToken(); // Método para generar un token de recuperación
                usuario.setLogin(token);
                usuarioRepository.save(usuario);

                String resetUrl = "http://localhost:8080/resetPassword?token=" + token;
                Email.enviarEmail(email, "Recuperación de contraseña",
                        "Para restablecer tu contraseña, haz clic en el siguiente enlace: " + resetUrl);
            }

            return "mensajeRecuperacionEnviado";
        }

        private String generarToken() {
            return java.util.UUID.randomUUID().toString();
        }
    }


