/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.IncidentesRegistrados;

/**
 *
 * @author cordo
 */
public interface IIncidentesRegistradosService {
    
    public Optional<List<IncidentesRegistrados>> findAll();

    public Optional<IncidentesRegistrados> findById(Long id);

    public IncidentesRegistrados create(IncidentesRegistrados incidentesRegistrados);

    public Optional<IncidentesRegistrados> update(IncidentesRegistrados incidentesRegistrados, Long id);

    public void delete(Long id);

    public void deleteAll();
}
