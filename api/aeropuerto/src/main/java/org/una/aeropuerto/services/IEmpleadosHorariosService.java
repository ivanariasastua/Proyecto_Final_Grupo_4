/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.EmpleadosHorarios;

/**
 *
 * @author cordo
 */
public interface IEmpleadosHorariosService {
    
    public Optional<List<EmpleadosHorarios>> findAll();

    public Optional<EmpleadosHorarios> findById(Long id);
    
    public EmpleadosHorarios create(EmpleadosHorarios empleadosHorarios);

    public Optional<EmpleadosHorarios> update(EmpleadosHorarios empleadosHorarios, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<EmpleadosHorarios>> findByEmpleadosId(Long id);
}
