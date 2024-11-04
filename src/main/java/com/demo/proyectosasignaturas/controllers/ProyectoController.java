package com.demo.proyectosasignaturas.controllers;


import com.demo.proyectosasignaturas.model.Proyecto;
import com.demo.proyectosasignaturas.model.ProyectoRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;
import java.util.List;

/**
 * Controlador para gestionar las operaciones relacionadas con proyectos.
 */
@Controller
public class ProyectoController {

    @Autowired
    private ProyectoRepository proyectoRepository;

    /**
     * Muestra todos los proyectos en la vista correspondiente.
     *
     * @param model el modelo para la vista que utiliza ThymeLeaf
     * @return el nombre de la vista donde se muestran los proyectos
     */
    @GetMapping("/proyectos")
    public String proyectos(Model model) {
        List<Proyecto> listaProyectos = proyectoRepository.findAll();
        model.addAttribute("proyectos", listaProyectos);
        System.out.println(listaProyectos);
        return "proyectos";
    }

    /**
     * Muestra el formulario para crear un nuevo proyecto.
     *
     * @param model el modelo para la vista
     * @return el nombre de la vista para crear un nuevo proyecto
     */
    @GetMapping("/proyectos/NuevoProyecto")
    public String proyectoNuevo(Model model) {
        model.addAttribute("proyecto", new Proyecto());
        model.addAttribute("pageTitle", "Nuevo Proyecto");

        return "NuevoProyecto";
    }

    /**
     * Guarda un nuevo proyecto o actualiza uno existente.
     *
     * @param proyecto el proyecto a guardar
     * @param redirectAttributes atributos para redirigir y mostrar mensajes
     * @return la redirección a la lista de proyectos
     */
    @PostMapping("/proyectos/guardar")
    public String guardarNuevoProyecto(Proyecto proyecto, RedirectAttributes redirectAttributes) {
        try{
            // Si el proyecto ya existe, lo actualizamos
            if (proyecto.getIdProyecto() != null){

                Proyecto proyectoExiste = proyectoRepository.getReferenceById(Long.valueOf(proyecto.getIdProyecto()));

                proyectoExiste.setNombreProyecto(proyecto.getNombreProyecto());
                proyectoExiste.setAsignatura(proyecto.getAsignatura());
                proyectoExiste.setNota(proyecto.getNota());

                proyectoRepository.save(proyectoExiste);
                redirectAttributes.addFlashAttribute("message", "Proyecto guardado correctamente");
            }
            // Si es un nuevo proyecto, lo guardamos
            else{
                proyectoRepository.save(proyecto);
                redirectAttributes.addFlashAttribute("message", "Proyecto agregado correctamente");
            }
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/proyectos"; // Redirige a la lista de proyectos
    }

    /**
     * Muestra el formulario para editar un proyecto existente.
     *
     * @param idProyecto el ID del proyecto a editar
     * @param model el modelo para la vista
     * @param redirectAttributes atributos para redirigir y mostrar mensajes
     * @return el nombre de la vista para editar el proyecto
     */
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
        return "redirect:/proyectos"; // Redirige a la lista de proyectos en caso de error
    }


    /**
     * Elimina un proyecto existente.
     *
     * @param id el ID del proyecto a eliminar
     * @param redirectAttributes atributos para redirigir y mostrar mensajes
     * @return la redirección a la lista de proyectos
     */
    @GetMapping("/proyectos/delete/{id}")
    public String eliminarProyecto(@PathVariable("id") Integer id, RedirectAttributes redirectAttributes) {
        try {
            proyectoRepository.deleteById(Long.valueOf(id));
            redirectAttributes.addAttribute("mensaje", "Proyecto eliminado correctamente");
        }catch (Exception e){
            redirectAttributes.addFlashAttribute("message", e.getMessage());
        }
        return "redirect:/proyectos"; // Redirige a la lista de proyectos
    }
}
