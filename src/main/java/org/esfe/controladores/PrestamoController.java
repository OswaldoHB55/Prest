package org.esfe.controladores;


import org.esfe.modelos.Prestamo;
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
@RequestMapping("/prestamos")
public class PrestamoController {
    @Autowired
    private IPrestamoService prestamoService;
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
    public String create(Prestamo prestamo){
        return "prestamo/create";
    }
    @PostMapping("/save")
    public String save(Prestamo prestamo, BindingResult result, Model model, RedirectAttributes attributes){
        if(result.hasErrors()){
            model.addAttribute(prestamo);
            attributes.addFlashAttribute("error", "No se pudo guardar debeido a un error");
            return "prestamo/create";
        }
        prestamoService.createOEditOne(prestamo);
        attributes.addFlashAttribute("msg", "Prestamo creado correctamente");
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
        model.addAttribute("prestamo", prestamo);
        return "prestamo/edit";
    }
    @GetMapping("/remove/{id}")
    public String remove(@PathVariable("id")Integer id, Model model) {
        Prestamo prestamo = prestamoService.buscarPorId(id).get();
        model.addAttribute("prestamo", prestamo);
        return "prestamo/delete";
    }


}
