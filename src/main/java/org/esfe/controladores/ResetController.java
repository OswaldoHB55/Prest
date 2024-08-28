package org.esfe.controladores;

import org.esfe.modelos.Usuario;

import org.esfe.repositorios.UsuarioRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@Controller
public class ResetController {


        @Autowired
        private UsuarioRepository usuarioRepository;

        @GetMapping("/resetPassword")
        public String mostrarFormularioRestablecer(@RequestParam("token") String token) {
            Usuario usuario = usuarioRepository.findByToken(token);

            if (usuario == null) {
                // manejar error si el token no es válido
                return "error";
            }

            return "resetPassword";
        }

        @PostMapping("/resetPassword")
        public String resetPassword(@RequestParam("token") String token,
                                    @RequestParam("newPassword") String newPassword) {
            Usuario usuario = usuarioRepository.findByToken(token);

            if (usuario == null) {
                // manejar error si el token no es válido
                return "error";
            }

            usuario.setClave(newPassword); // Aquí puedes aplicar algún hash antes de guardar
            usuario.setLogin(null);
            usuarioRepository.save(usuario);

            return "passwordRestablecida";
        }

}
