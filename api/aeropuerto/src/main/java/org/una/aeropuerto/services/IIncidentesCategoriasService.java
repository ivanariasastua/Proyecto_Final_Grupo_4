/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.IncidentesCategoriasDTO;
import org.una.aeropuerto.entities.IncidentesCategorias;

/**
 *
 * @author cordo
 */
public interface IIncidentesCategoriasService {

    public Optional<List<IncidentesCategoriasDTO>> findAll();

    public Optional<IncidentesCategoriasDTO> findById(Long id);

    public IncidentesCategoriasDTO create(IncidentesCategoriasDTO incidentesCategorias);

    public Optional<IncidentesCategoriasDTO> update(IncidentesCategoriasDTO incidentesCategorias, Long id);

    public void delete(Long id);

    public void deleteAll();

    public Optional<List<IncidentesCategoriasDTO>> findByNombre(String nombre);
}
