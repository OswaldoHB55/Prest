package org.esfe.controladores;

import org.esfe.modelos.Cliente;
import org.esfe.modelos.Pago;
import org.esfe.modelos.Prestamo;
import org.esfe.servicios.interfaces.IClienteService;
import org.esfe.servicios.interfaces.IPagoService;
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

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.IntStream;

@Controller
@RequestMapping("/pagos")
public class PagoController  {
    @Autowired
    private IPrestamoService prestamoService;

    @Autowired
    private IPagoService pagoService;

    @GetMapping
    public String index(Model model, @RequestParam("page") Optional<Integer> page, @RequestParam("size") Optional<Integer> size){
        int currentPage = page.orElse(1) - 1; //Si esta seteando se asigna 0
        int pageSize = size.orElse(5); //Tamaño de la pagina, se asigna 5
        Pageable pageable = PageRequest.of(currentPage, pageSize);

        Page<Pago> pagos = pagoService.buscarTodosPaginados(pageable);
        model.addAttribute("pagos", pagos);

        int totalPages = pagos.getTotalPages();
        if (totalPages > 0){
            List<Integer> pageNumbers = IntStream.rangeClosed(1, totalPages).boxed().collect(Collectors.toList());
            model.addAttribute("pageNumbers", pageNumbers);
        }
        return "pago/index";

    }
    @GetMapping("/create")
    public String create(Pago pago,  Model model){
        model.addAttribute("prestamos", prestamoService.obtenerTodos());
        return "pago/create";
    }


    @PostMapping("/save")
    public String save(@RequestParam("prestamo_id") Integer prestamo, Pago pago, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(prestamo);
            model.addAttribute("prestamos", prestamoService.obtenerTodos());
            attributes.addFlashAttribute("error", "No se pudo guardar debeido a un error");
            return "pago/create";
        }

        Prestamo perfil = new Prestamo();
        perfil.setId(prestamo);

        pago.setPrestamo(perfil);
        pagoService.createOEditOne(pago);
        attributes.addFlashAttribute("msg", "Pago creado correctamente");
        return "redirect:/pagos";
    }

    @GetMapping("/details/{id}")
    public String details(@PathVariable("id")Integer id, Model model){
        Pago pago = pagoService.buscarPorId(id).get();
        model.addAttribute("pago", pago);
        return "pago/details";
    }

    @GetMapping("/edit/{id}")
    public String edit(@PathVariable("id")Integer id, Model model) {
        Pago pago = pagoService.buscarPorId(id).get();
        model.addAttribute("prestamos", prestamoService.obtenerTodos());
        model.addAttribute("pago", pago);
        return "pago/edit";
    }

    @PostMapping("/update")
    public String update(@RequestParam Integer id, @RequestParam Integer prestamoId, @RequestParam Integer monto_pago,
                         @RequestParam String fecha_pago, @RequestParam String metodo_pago, @RequestParam String estado_pago, RedirectAttributes attributes) {
        Pago pago = pagoService.buscarPorId(id).get();
        Prestamo prestamo = prestamoService.buscarPorId(prestamoId).get();

        if(prestamo != null) {
            pago.setPrestamo(prestamo);
            pago.setMonto_pago(monto_pago);
            pago.setFecha_pago(fecha_pago);
            pago.setMetodo_pago(metodo_pago);
            pago.setEstado_pago(estado_pago);


            pagoService.createOEditOne(pago);
            attributes.addFlashAttribute("msg", "Pago modificado correctamente");
        } else {
            attributes.addFlashAttribute("error", "Préstamo o cliente no encontrado");
        }
        return "redirect:/pagos";
    }


    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id")Integer id, Model model) {
        Pago pago = pagoService.buscarPorId(id).get();
        model.addAttribute("pago", pago);
        return "pago/delete";
    }

    @PostMapping("/delete")
    public String delete (Pago pago, RedirectAttributes attributes){
        pagoService.eliminarPorId(pago.getId());
        attributes.addFlashAttribute("msg", "pagos eliminado correctamnete");
        return "redirect:/pagos";
    }
}
