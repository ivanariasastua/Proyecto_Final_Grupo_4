/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.entities.EmpleadosMarcajes;
import org.una.aeropuerto.repositories.IEmpleadosMarcajesRepository;

/**
 *
 * @author cordo
 */
@Service
public class EmpleadosMarcajesServiceImplementation implements IEmpleadosMarcajesService{

    @Autowired
    private IEmpleadosMarcajesRepository empleadoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadosMarcajes> findById(Long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadosMarcajes>> findAll() {
        return Optional.ofNullable(empleadoRepository.findAll());
    }

    @Override
    @Transactional
    public EmpleadosMarcajes create(EmpleadosMarcajes empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Optional<EmpleadosMarcajes> update(EmpleadosMarcajes empleado, Long id) {
        if(empleadoRepository.findById(id).isPresent())
            return Optional.ofNullable(empleadoRepository.saveAndFlush(empleado));
        else
            return null;
    }
    
    @Override
    public void delete(Long id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        empleadoRepository.deleteAll();
    }
    
}
