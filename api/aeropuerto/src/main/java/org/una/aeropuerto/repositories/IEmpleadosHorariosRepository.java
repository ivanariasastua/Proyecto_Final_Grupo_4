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
import org.una.aeropuerto.entities.EmpleadosHorarios;

/**
 *
 * @author cordo
 */
public interface IEmpleadosHorariosRepository extends JpaRepository<EmpleadosHorarios, Long>{
    
    @Query("Select e From EmpleadosHorarios e WHERE e.empleado.id = :id and e.estado = true")
    public List<EmpleadosHorarios> getHorariosMarcajes(@Param("id")Long id);
}
