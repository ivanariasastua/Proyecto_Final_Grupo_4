/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.EmpleadosHorarios;

/**
 *
 * @author cordo
 */
public interface IEmpleadosHorariosRepository extends JpaRepository<EmpleadosHorarios, Long>{
    
    @Query("update EmpleadosHorarios eh set eh.estado = 0 where eh.id = id")
    public void inactivar(Long id);
}
