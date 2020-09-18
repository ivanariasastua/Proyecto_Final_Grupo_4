/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Empleados;

/**
 *
 * @author cordo
 */
public interface IEmpleadosRepository extends JpaRepository<Empleados, Long>{
    
    public List<Empleados> findByUsuariosId(Long id);
    
    public List<Empleados> findByNombre(String nombre);
    
    public List<Empleados> findByCedula(String cedula);
}
