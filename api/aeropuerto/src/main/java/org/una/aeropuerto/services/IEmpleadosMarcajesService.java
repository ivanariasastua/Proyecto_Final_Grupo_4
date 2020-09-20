/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.EmpleadosMarcajesDTO;
import org.una.aeropuerto.entities.EmpleadosMarcajes;

/**
 *
 * @author cordo
 */
public interface IEmpleadosMarcajesService {
    public Optional<List<EmpleadosMarcajesDTO>> findAll();

    public Optional<EmpleadosMarcajesDTO> findById(Long id);
    
    public EmpleadosMarcajesDTO create(EmpleadosMarcajesDTO empleadosMarcajes);

    public Optional<EmpleadosMarcajesDTO> update(EmpleadosMarcajesDTO empleadosMarcajes, Long id);
}
