package com.demo.proyectosasignaturas.controllers;
import com.demo.proyectosasignaturas.model.Proyecto;
import com.demo.proyectosasignaturas.model.ProyectoRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.ui.Model;
import static org.junit.jupiter.api.Assertions.assertEquals;

import java.util.Arrays;
import java.util.List;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

class ProyectoControllerTest {
    @InjectMocks
    private ProyectoController proyectoController;

    @Mock
    private ProyectoRepository proyectoRepository;

    @Mock
    private Model model;

    @Mock
    private RedirectAttributes redirectAttributes;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    @DisplayName("retornar la vista de proyectos y agregar la lista de proyectos al modelo")

    public void testProyectos(){

        Proyecto proyecto1 = new Proyecto();
        proyecto1.setIdProyecto(1);
        proyecto1.setNombreProyecto("Proyecto 1");

        Proyecto proyecto2 = new Proyecto();
        proyecto2.setIdProyecto(2);
        proyecto2.setNombreProyecto("Proyecto 2");

        List<Proyecto> listaProyectos = Arrays.asList(proyecto1, proyecto2);
        when(proyectoRepository.findAll()).thenReturn(listaProyectos);

        String view = proyectoController.proyectos(model);

        assertEquals("proyectos", view);
        verify(model).addAttribute("proyectos", listaProyectos);
    }

    @Test
    @DisplayName("guarda un nuevo proyecto y redirige a la lista de proyectos")
    public void testGuardarNuevoProyecto() {
        Proyecto proyecto = new Proyecto();
        proyecto.setIdProyecto(null);
        proyecto.setNombreProyecto("Nuevo Proyecto");

        String viewName = proyectoController.guardarNuevoProyecto(proyecto, redirectAttributes);

        assertEquals("redirect:/proyectos", viewName);
        verify(proyectoRepository).save(proyecto);
        verify(redirectAttributes).addFlashAttribute("message", "Proyecto agregado correctamente");
    }

    @Test
    @DisplayName("elimina un proyecto y redirige a la lista de proyectos")
    public void testEliminarProyecto() {
        Integer id = 1;

        String viewName = proyectoController.eliminarProyecto(id, redirectAttributes);

        assertEquals("redirect:/proyectos", viewName);
        verify(proyectoRepository).deleteById(Long.valueOf(id));
        verify(redirectAttributes).addFlashAttribute("message", "Proyecto eliminado correctamente");
    }
}