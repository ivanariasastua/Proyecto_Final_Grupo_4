/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.AreasTrabajos;

/**
 *
 * @author cordo
 */
public interface IAreasTrabajosService {
    
    public Optional<List<AreasTrabajos>> findAll();

    public Optional<AreasTrabajos> findById(Long id);
    
    public AreasTrabajos create(AreasTrabajos areasTrabajos);

    public Optional<AreasTrabajos> update(AreasTrabajos areasTrabajos, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<AreasTrabajos>> findByNombre(String nombre);

}
