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

/**
 * Clase que representa un proyecto.
 * Esta entidad es persistente en la base de datos y contiene
 * la información relacionada con un proyecto específico.
 */
@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor

public class Proyecto {

    /**
     * Identificador único del proyecto.
     * Este campo se genera automáticamente en la base de datos.
     */
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer idProyecto;

    /**
     * Nombre del proyecto.
     * No debe estar vacío.
     */
    @NotBlank(message = "El nombre no debe ir vacio")
    private String nombreProyecto;

    /**
     * Asignatura relacionada con el proyecto.
     * No debe estar vacío.
     */
    @NotBlank(message = "asignatura no debe estar vacia")
    private String asignatura;

    /**
     * Nota asignada al proyecto.
     * Debe estar en el rango de 0 a 10, inclusivo.
     */
    @Min(value = 0, message = "la nota debe ser al menos 0")
    @Max(value = 10, message = "la nota no puede ser superior a 10")
    private int nota;

}
