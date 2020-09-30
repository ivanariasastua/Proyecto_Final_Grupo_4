/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.AreasTrabajos;

/**
 *
 * @author cordo
 */
public interface IAreasTrabajosRepository extends JpaRepository<AreasTrabajos, Long>{
    @Query("select u from AreasTrabajos u where UPPER(u.nombre) like CONCAT('%', UPPER(:nombre), '%')")
    public List<AreasTrabajos> findByNombre(String nombre);

    public List<AreasTrabajos> findByEstado(boolean estado);
}
