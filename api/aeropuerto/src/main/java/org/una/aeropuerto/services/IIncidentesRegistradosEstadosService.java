/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.IncidentesRegistradosEstadosDTO;

/**
 *
 * @author cordo
 */
public interface IIncidentesRegistradosEstadosService {

    public Optional<List<IncidentesRegistradosEstadosDTO>> findAll();

    public Optional<IncidentesRegistradosEstadosDTO> findById(Long id);

    public IncidentesRegistradosEstadosDTO create(IncidentesRegistradosEstadosDTO incidentesRegistradosEstados);

    public Optional<IncidentesRegistradosEstadosDTO> update(IncidentesRegistradosEstadosDTO incidentesRegistradosEstados, Long id);

    public Optional<List<IncidentesRegistradosEstadosDTO>> findByIncidentesEstadosId(Long id);

    public Optional<List<IncidentesRegistradosEstadosDTO>> findByIncidentesRegistradosId(Long id);
}
