package com.demo.proyectosasignaturas.model;

import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Repositorio para la entidad Proyecto.
 * Extiende JpaRepository para proporcionar métodos de acceso a datos
 * y operaciones CRUD (Crear, Leer, Actualizar, Eliminar) para la
 * entidad Proyecto.
 */
public interface ProyectoRepository extends JpaRepository<Proyecto, Long> {
    // No es necesario implementar métodos adicionales, ya que JpaRepository
    // proporciona implementaciones para las operaciones básicas.
}
