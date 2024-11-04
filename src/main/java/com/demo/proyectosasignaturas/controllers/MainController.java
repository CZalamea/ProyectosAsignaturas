package com.demo.proyectosasignaturas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;


/**
 * Controlador principal de la aplicación.
 * Este controlador maneja las solicitudes HTTP relacionadas con la página de inicio.
 */
@Controller
public class MainController {

    /**
     * MétodoO que maneja las solicitudes GET a la raíz de la aplicación.
     *
     * @return El nombre de la vista que se debe renderizar, en este caso "index".
     */
    @GetMapping("")
    public String index() {
        // Mensaje en la consola para verificar que se ha accedido al controlador principal

        System.out.println("main controller");
        return "index"; // Retorna el nombre de la vista "index"
    }
}
