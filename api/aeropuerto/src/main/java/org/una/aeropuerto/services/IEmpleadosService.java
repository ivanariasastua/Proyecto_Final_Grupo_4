/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Empleados;

/**
 *
 * @author cordo
 */
public interface IEmpleadosService {
    
    public Optional<List<Empleados>> findAll();

    public Optional<Empleados> findById(Long id);
    
    public Empleados create(Empleados empleados);

    public Optional<Empleados> update(Empleados empleados, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<Empleados>> findByUsuariosId(Long id);
    
    public Optional<List<Empleados>> findByNombre(String nombre);
    
    public Optional<List<Empleados>> findByCedula(String cedula);
}
