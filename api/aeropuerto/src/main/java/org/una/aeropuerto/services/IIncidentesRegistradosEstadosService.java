/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.IncidentesRegistradosEstados;

/**
 *
 * @author cordo
 */
public interface IIncidentesRegistradosEstadosService {

    public Optional<List<IncidentesRegistradosEstados>> findAll();

    public Optional<IncidentesRegistradosEstados> findById(Long id);

    public IncidentesRegistradosEstados create(IncidentesRegistradosEstados incidentesRegistradosEstados);

    public Optional<IncidentesRegistradosEstados> update(IncidentesRegistradosEstados incidentesRegistradosEstados, Long id);

    public void delete(Long id);

    public void deleteAll();

    public Optional<List<IncidentesRegistradosEstados>> findByIncidentesEstadosId(Long id);

    public Optional<List<IncidentesRegistradosEstados>> findByIncidentesRegistradosId(Long id);
}
