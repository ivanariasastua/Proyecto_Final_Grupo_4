/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.IncidentesCategoriasDTO;

/**
 *
 * @author cordo
 */
public interface IIncidentesCategoriasService {


    public IncidentesCategoriasDTO create(IncidentesCategoriasDTO incidentesCategorias);

    public Optional<IncidentesCategoriasDTO> update(IncidentesCategoriasDTO incidentesCategorias, Long id);

    public Optional<List<IncidentesCategoriasDTO>> findByNombre(String nombre);
    
    public Optional<List<IncidentesCategoriasDTO>> findByEstado(boolean estado);
}
