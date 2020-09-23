/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.AreasTrabajosDTO;

/**
 *
 * @author cordo
 */
public interface IAreasTrabajosService {
    
    public Optional<List<AreasTrabajosDTO>> findAll();

    public Optional<AreasTrabajosDTO> findById(Long id);
    
    public AreasTrabajosDTO create(AreasTrabajosDTO areasTrabajos);

    public Optional<AreasTrabajosDTO> update(AreasTrabajosDTO areasTrabajos, Long id);
    
    public Optional<List<AreasTrabajosDTO>> findByNombre(String nombre);
    
    public Optional<AreasTrabajosDTO> inactivate(Long id);
    
}
