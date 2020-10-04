/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.EmpleadosAreasTrabajos;

/**
 *
 * @author cordo
 */
public interface IEmpleadosAreasTrabajosRepository extends JpaRepository<EmpleadosAreasTrabajos, Long>{
    
 
    @Query("Select e from EmpleadosAreasTrabajos e "+
            "join e.areaTrabajo eat on e.id = eat.id "+
            "where UPPER(eat.nombre) like CONCAT('%', UPPER(:area), '%')")
    public List<EmpleadosAreasTrabajos> findByAreas(@Param("area")String area);

}
