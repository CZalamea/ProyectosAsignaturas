package com.demo.proyectosasignaturas.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class MainController {

    @GetMapping("")
    public String index() {
        System.out.println("main controller");
        return "index";
    }
}
