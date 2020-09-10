/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.IncidentesEstados;

/**
 *
 * @author cordo
 */
public interface IIncidentesEstadosService {
    
    public Optional<List<IncidentesEstados>> findAll();

    public Optional<IncidentesEstados> findById(Long id);

    public IncidentesEstados create(IncidentesEstados incidentesEstados);

    public Optional<IncidentesEstados> update(IncidentesEstados incidentesEstados, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<IncidentesEstados>> findByEstado(String estado);
}
