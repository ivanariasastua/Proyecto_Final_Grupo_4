/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.EmpleadosDTO;
import org.una.aeropuerto.entities.Empleados;

/**
 *
 * @author cordo
 */
public interface IEmpleadosService {
    
    public Optional<List<EmpleadosDTO>> findAll();

    public Optional<EmpleadosDTO> findById(Long id);
    
    public Optional<Empleados> findByCedula(String cedula);
    
    public EmpleadosDTO create(EmpleadosDTO empleados);

    public Optional<EmpleadosDTO> update(EmpleadosDTO empleados, Long id);

    public Optional<List<EmpleadosDTO>> filtro(String nombre, String cedula, boolean estado, String area);

    public Optional<EmpleadosDTO> inactivate(Long id);
}
