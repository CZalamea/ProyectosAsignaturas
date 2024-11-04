package com.demo.proyectosasignaturas.model;

import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.NotBlank;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Proyecto {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProyecto;

    @NotBlank(message = "El nombre no debe ir vacio")
    private String nombreProyecto;

    @NotBlank(message = "asignatura no debe estar vacia")
    private String asignatura;

    @Min(value = 0, message = "la nota debe ser al menos 0")
    @Max(value = 10, message = "la nota no puede ser superior a 10")
    private int nota;

}
