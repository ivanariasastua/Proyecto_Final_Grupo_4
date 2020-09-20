/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.IncidentesEstados;

/**
 *
 * @author cordo
 */
public interface IIncidentesEstadosRepository extends JpaRepository<IncidentesEstados, Long>{
    public List<IncidentesEstados> findByEstado(String nombre);
    
}
