/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.Empleados;

/**
 *
 * @author cordo
 */
public interface IEmpleadosRepository extends JpaRepository<Empleados, Long>{
    
    @Query("Select e from Empleados e "+
            "join e.empleadosAreasTrabajo eat on e.id = eat.empleado "+
            "where UPPER(e.nombre) like :nombre and UPPER(e.cedula) like :cedula and e.estado =:estado and eat.areaTrabajo.nombre =:area")
    public List<Empleados> filtro(String nombre, String cedula, boolean estado, String area);

    @Query("update Empleados em set em.estado = 0 where em.id = id")
    public void inactivar(Long id);
}
