package org.esfe.controladores;


import org.esfe.modelos.Cliente;
import org.esfe.modelos.Prestamo;
import org.esfe.servicios.interfaces.IClienteService;
import org.esfe.servicios.interfaces.IPrestamoService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/prestamos")
public class PrestamoController {
    @Autowired
    private IPrestamoService prestamoService;

    @Autowired
    private IClienteService clienteService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1; //Si esta seteando se asigna 0
        int pageSize = size.orElse(5); //Tamaño de la pagina, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Prestamo> prestamos =prestamoService.buscarTodosPaginados(pageable);
        model.addAttribute("prestamos", prestamos);

        int totalPages = prestamos.getTotalPages();
        if (totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "prestamo/index";

    }
    @GetMapping("/create")
    public String create(Prestamo prestamo,  Model model){
        model.addAttribute("clientes", clienteService.obtenerTodos());
        return "prestamo/create";
    }


    @PostMapping("/save")
    public String save(@RequestParam("cliente_id") Integer cliente, Prestamo prestamo, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(prestamo);
            model.addAttribute("clientes", clienteService.obtenerTodos());
            attributes.addFlashAttribute("error", "No se pudo guardar debido a un error");
            return "prestamo/create";
        }

        Cliente perfil = new Cliente();
        perfil.setId(cliente);

        prestamo.setCliente(perfil);

        // Inicializar monto_restante con el monto total del préstamo
        prestamo.setMonto_restante(prestamo.getMonto());

        // Establecer el estado inicial del préstamo
        prestamo.setEstado("Activo");

        prestamoService.createOEditOne(prestamo);
        attributes.addFlashAttribute("msg", "Préstamo creado correctamente");
        return "redirect:/prestamos";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id")Integer id, Model model){
        Prestamo prestamo = prestamoService.buscarPorId(id).get();
        model.addAttribute("prestamo", prestamo);
        return "prestamo/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id")Integer id, Model model) {
        Prestamo prestamo = prestamoService.buscarPorId(id).get();
        model.addAttribute("clientes", clienteService.obtenerTodos());
        model.addAttribute("prestamo", prestamo);
        return "prestamo/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer id, @RequestParam Integer clienteId, @RequestParam Integer monto,
                         @RequestParam Integer interes, @RequestParam String plazo, @RequestParam Date fecha_inicio,
                         @RequestParam Date fecha_final, @RequestParam String estado, RedirectAttributes attributes) {
        Prestamo prestamo = prestamoService.buscarPorId(id).get();
        Cliente cliente = clienteService.buscarPorId(clienteId).get();

        if(cliente != null) {
            prestamo.setCliente(cliente);
            prestamo.setMonto(monto);
            prestamo.setInteres(interes);
            prestamo.setPlazo(plazo);
            prestamo.setFecha_inicio(fecha_inicio);
            prestamo.setFecha_final(fecha_final);
            prestamo.setEstado(estado);

            // Actualizar monto_restante solo si el préstamo está en estado "Activo"
            if (estado.equals("Activo")) {
                // Si el monto cambia, ajustar el monto_restante proporcionalmente
                if (prestamo.getMonto() != monto) {
                    double proporcion = (double) monto / prestamo.getMonto();
                    int nuevoMontoRestante = (int) (prestamo.getMonto_restante() * proporcion);
                    prestamo.setMonto_restante(nuevoMontoRestante);
                }
            } else if (estado.equals("Pagado")) {
                prestamo.setMonto_restante(0);
            }

            prestamoService.createOEditOne(prestamo);
            attributes.addFlashAttribute("msg", "Préstamo modificado correctamente");
        } else {
            attributes.addFlashAttribute("error", "Préstamo o cliente no encontrado");
        }
        return "redirect:/prestamos";
    }


    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id")Integer id, Model model) {
        Prestamo prestamo = prestamoService.buscarPorId(id).get();
        model.addAttribute("prestamo", prestamo);
        return "prestamo/delete";
    }

    @PostMapping("/delete")
    public String delete (Prestamo prestamo, RedirectAttributes attributes){
        prestamoService.eliminarPorId(prestamo.getId());
        attributes.addFlashAttribute("msg", "Prestamo eliminado correctamnete");
        return "redirect:/prestamos";
    }

}
