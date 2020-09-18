/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.EmpleadosMarcajes;

/**
 *
 * @author cordo
 */
public interface IEmpleadosMarcajesService {
    public Optional<List<EmpleadosMarcajes>> findAll();

    public Optional<EmpleadosMarcajes> findById(Long id);
    
    public EmpleadosMarcajes create(EmpleadosMarcajes empleadosMarcajes);

    public Optional<EmpleadosMarcajes> update(EmpleadosMarcajes empleadosMarcajes, Long id);

    public void delete(Long id);

    public void deleteAll();
}
