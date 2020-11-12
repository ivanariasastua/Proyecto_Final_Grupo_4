/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.Date;
import java.util.List;
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
    
    @Query("SELECT mar FROM EmpleadosMarcajes mar "
         + "WHERE mar.empleadoHorario.empleado.cedula LIKE CONCAT('%',:cedula,'%') "
         + "AND mar.fechaRegistro BETWEEN :fecha1 AND :fecha2 AND mar.empleadoHorario.estado = true")
    public List<EmpleadosMarcajes> findByEmpleadoCedulaAndFechas(@Param("cedula") String cedula, @Param("fecha1") Date fecha1, 
                                                                     @Param("fecha2") Date fecha2);
}
