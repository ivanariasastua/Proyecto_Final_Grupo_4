/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.EmpleadosAreasTrabajos;

/**
 *
 * @author cordo
 */
public interface IEmpleadosAreasTrabajosService {
    
    public Optional<List<EmpleadosAreasTrabajos>> findAll();

    public Optional<EmpleadosAreasTrabajos> findById(Long id);
    
    public EmpleadosAreasTrabajos create(EmpleadosAreasTrabajos empleadosAreasTrabajos);

    public Optional<EmpleadosAreasTrabajos> update(EmpleadosAreasTrabajos empleadosAreasTrabajos, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<EmpleadosAreasTrabajos>> findByEmpleadoId(Long id);
    
    public Optional<List<EmpleadosAreasTrabajos>> findByAreaTrabajoId(Long id);
}
