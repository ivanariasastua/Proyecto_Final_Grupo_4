/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.Empleados;

/**
 *
 * @author cordo
 */
public interface IEmpleadosRepository extends JpaRepository<Empleados, Long>{
    
    public List<Empleados> findByCedulaContaining(String cedula);
    
    public Empleados findByCedula(String cedula);

    @Query("update Empleados em set em.estado = 0 where em.id = id")
    public void inactivar(Long id);
    
    public List<Empleados> findByNombreContaining(String nombreCompleto);
    
    @Query("Select e from Empleados e "+
            "join e.empleadosAreasTrabajo eat on e.id = eat.empleado "+
            "where UPPER(eat.areaTrabajo.nombre) like CONCAT('%', UPPER(:area), '%') "+
            "and e.estado = true")
    public List<Empleados> findByAreas(@Param("area")String area);
    
    public Empleados findByCedulaAndContrasenaEncriptada(String cedula, String contrasenaEncriptada);

    @Query("Select e from Empleados e "+
           "Where e.estado = 1 and e.aprobado = 0 and e.rol.id = :rol")
    public List<Empleados> findByAprobadoFalseAndEstadoTrueAndRol(@Param("rol") Long rol);
    
}
