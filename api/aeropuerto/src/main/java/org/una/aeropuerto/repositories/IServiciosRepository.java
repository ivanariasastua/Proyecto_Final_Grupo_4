/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.Servicios;

/**
 *
 * @author cordo
 */
public interface IServiciosRepository extends JpaRepository<Servicios, Long>{
    public List<Servicios> findByNombre(String nombre);
    
    @Query("update Servicios s set s.estado = 0 where s.id = id")
    public void inactivar(Long id);
}
