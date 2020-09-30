/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.IncidentesCategorias;

/**
 *
 * @author cordo
 */
public interface IIncidentesCategoriasRepository extends JpaRepository<IncidentesCategorias, Long>{
    @Query("select u from IncidentesCategorias u where UPPER(u.nombre) like CONCAT('%', UPPER(:nombre), '%')")
    public List<IncidentesCategorias> findByNombre(String nombre);
    
    public List<IncidentesCategorias> findByEstado(boolean estado);
}
