/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.EmpleadosHorariosDTO;

/**
 *
 * @author cordo
 */
public interface IEmpleadosHorariosService {
    
    public Optional<List<EmpleadosHorariosDTO>> findAll();

    public Optional<EmpleadosHorariosDTO> findById(Long id);
    
    public EmpleadosHorariosDTO create(EmpleadosHorariosDTO empleadosHorarios);

    public Optional<EmpleadosHorariosDTO> update(EmpleadosHorariosDTO empleadosHorarios, Long id);

    public Optional<EmpleadosHorariosDTO> inactivate(Long id);
}
