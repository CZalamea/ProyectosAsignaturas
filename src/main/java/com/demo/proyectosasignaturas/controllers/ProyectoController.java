package com.demo.proyectosasignaturas.controllers;


import com.demo.proyectosasignaturas.model.Proyecto;
import com.demo.proyectosasignaturas.model.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

@Controller
public class ProyectoController {

    @Autowired
    private ProyectoRepository proyectoRepository;

    //muestra todos los proyectos

    @GetMapping("/proyectos")
    public String proyectos(Model model) {
        List<Proyecto> listaProyectos = proyectoRepository.findAll();
        model.addAttribute("proyectos", listaProyectos);
        System.out.println(listaProyectos);
        return "proyectos";
    }

    @GetMapping("/proyectos/NuevoProyecto")
    public String proyectoNuevo(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("pageTitle", "Nuevo Proyecto");

        return "NuevoProyecto";
    }

    @PostMapping("/proyectos/guardar")
    public String guardarNuevoProyecto(Proyecto proyecto, RedirectAttributes redirectAttributes) {
        try{
            if (proyecto.getIdProyecto() != null){

                Proyecto proyectoExiste = proyectoRepository.getReferenceById(Long.valueOf(proyecto.getIdProyecto()));

                proyectoExiste.setNombreProyecto(proyecto.getNombreProyecto());
                proyectoExiste.setAsignatura(proyecto.getAsignatura());
                proyectoExiste.setNota(proyecto.getNota());

                proyectoRepository.save(proyectoExiste);
                redirectAttributes.addFlashAttribute("message", "Proyecto guardado correctamente");
            }
            else{
                proyectoRepository.save(proyecto);
                redirectAttributes.addFlashAttribute("message", "Proyecto agregado correctamente");
            }
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/proyectos";
    }

    @GetMapping("proyectos/NuevoProyecto/{idProyecto}")
    public String editarProyecto(@PathVariable Integer idProyecto, Model model, RedirectAttributes redirectAttributes) {
        try{
            Proyecto proyecto = proyectoRepository.getReferenceById(Long.valueOf(idProyecto));
            model.addAttribute("proyecto", proyecto);
            model.addAttribute("pageTitle", "NuevoProyecto(ID: " + idProyecto + ")");
            return "NuevoProyecto";
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", "Error al editar proyecto");
        }
        return "redirect:/proyectos";
    }

    @GetMapping("/proyectos/delete/{id}")
    public String eliminarProyecto(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            proyectoRepository.deleteById(Long.valueOf(id));
            redirectAttributes.addAttribute("mensaje", "Proyecto eliminado correctamente");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/proyectos";
    }



}
