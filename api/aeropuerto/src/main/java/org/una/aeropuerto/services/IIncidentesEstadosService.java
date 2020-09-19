/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.IncidentesEstadosDTO;
import org.una.aeropuerto.entities.IncidentesEstados;

/**
 *
 * @author cordo
 */
public interface IIncidentesEstadosService {
    
    public Optional<List<IncidentesEstadosDTO>> findAll();

    public Optional<IncidentesEstadosDTO> findById(Long id);

    public IncidentesEstadosDTO create(IncidentesEstadosDTO incidentesEstados);

    public Optional<IncidentesEstadosDTO> update(IncidentesEstadosDTO incidentesEstados, Long id);

    public void inactive(Long id);

    public void inactiveAll();
    
    public Optional<List<IncidentesEstadosDTO>> findByEstado(String estado);
}
