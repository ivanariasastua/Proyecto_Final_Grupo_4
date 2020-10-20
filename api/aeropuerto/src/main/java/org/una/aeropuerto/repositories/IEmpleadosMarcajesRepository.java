/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.Optional;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.EmpleadosMarcajes;

/**
 *
 * @author cordo
 */
public interface IEmpleadosMarcajesRepository extends JpaRepository<EmpleadosMarcajes, Long>{
    
    @Query("SELECT MAX(em) FROM EmpleadosMarcajes em "+
           "WHERE em.empleadoHorario.id = :idHorario ")
    public Optional<EmpleadosMarcajes> findUltimoMarcaje(@Param("idHorario")Long idHorario);
}
