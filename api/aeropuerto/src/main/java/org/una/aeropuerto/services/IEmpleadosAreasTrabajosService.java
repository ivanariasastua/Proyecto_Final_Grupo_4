/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.EmpleadosAreasTrabajosDTO;

/**
 *
 * @author cordo
 */
public interface IEmpleadosAreasTrabajosService {
    
    public Optional<List<EmpleadosAreasTrabajosDTO>> findAll();

    public Optional<EmpleadosAreasTrabajosDTO> findById(Long id);
    
    public EmpleadosAreasTrabajosDTO create(EmpleadosAreasTrabajosDTO empleadosAreasTrabajos);

    public Optional<EmpleadosAreasTrabajosDTO> update(EmpleadosAreasTrabajosDTO empleadosAreasTrabajos, Long id);

    public Optional<EmpleadosAreasTrabajosDTO> inactivate(Long id);
}
