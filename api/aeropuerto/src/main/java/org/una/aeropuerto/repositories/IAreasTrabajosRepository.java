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
    
    public List<AreasTrabajos> findByNombre(String nombre);

    @Query("update AreasTrabajos at set at.estado = 0 where at.id = id")
    public void inactivar(Long id);
    
}
