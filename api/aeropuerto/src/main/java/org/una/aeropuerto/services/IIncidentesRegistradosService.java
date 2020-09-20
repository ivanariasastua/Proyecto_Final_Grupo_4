/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.IncidentesRegistradosDTO;
import org.una.aeropuerto.entities.IncidentesRegistrados;

/**
 *
 * @author cordo
 */
public interface IIncidentesRegistradosService {
    
    public Optional<List<IncidentesRegistradosDTO>> findAll();

    public Optional<IncidentesRegistradosDTO> findById(Long id);

    public IncidentesRegistradosDTO create(IncidentesRegistradosDTO incidentesRegistrados);

    public Optional<IncidentesRegistradosDTO> update(IncidentesRegistradosDTO incidentesRegistrados, Long id);

    public void inactive(Long id);

    public void inactiveAll();
    
    public Optional<List<IncidentesRegistradosDTO>> findByCategoriaId(Long id);
    
    public Optional<List<IncidentesRegistradosDTO>> findByAreaTrabajoId(Long id);
    
    public Optional<List<IncidentesRegistradosDTO>> filtro(String nomEmisor, String cedEmisor, String nomResponsable, String cedResponsable, String nomCategoria, String nomArea);
}
