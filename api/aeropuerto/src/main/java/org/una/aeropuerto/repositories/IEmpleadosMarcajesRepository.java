/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.EmpleadosMarcajes;

/**
 *
 * @author cordo
 */
public interface IEmpleadosMarcajesRepository extends JpaRepository<EmpleadosMarcajes, Long>{
    

}
