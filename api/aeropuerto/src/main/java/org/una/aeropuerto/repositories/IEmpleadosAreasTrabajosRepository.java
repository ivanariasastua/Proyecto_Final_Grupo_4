/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.EmpleadosAreasTrabajos;

/**
 *
 * @author cordo
 */
public interface IEmpleadosAreasTrabajosRepository extends JpaRepository<EmpleadosAreasTrabajos, Long>{
    
    public List<EmpleadosAreasTrabajos> findByEmpleado(Long id);
    
    public List<EmpleadosAreasTrabajos> findByAreaTrabajo(Long id);
    
}
