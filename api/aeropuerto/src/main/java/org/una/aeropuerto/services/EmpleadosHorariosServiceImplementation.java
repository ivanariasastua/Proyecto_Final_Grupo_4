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
import org.una.aeropuerto.entities.EmpleadosHorarios;
import org.una.aeropuerto.repositories.IEmpleadosHorariosRepository;

/**
 *
 * @author cordo
 */
@Service
public class EmpleadosHorariosServiceImplementation implements IEmpleadosHorariosService {

    @Autowired
    private IEmpleadosHorariosRepository empleadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadosHorarios> findById(Long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadosHorarios>> findAll() {
        return Optional.ofNullable(empleadoRepository.findAll());
    }

    @Override
    @Transactional
    public EmpleadosHorarios create(EmpleadosHorarios empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Optional<EmpleadosHorarios> update(EmpleadosHorarios empleado, Long id) {
        if (empleadoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(empleadoRepository.saveAndFlush(empleado));
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        empleadoRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        empleadoRepository.deleteAll();
    }

    @Override
    public Optional<List<EmpleadosHorarios>> findByEmpleadosId(Long id) {
        return Optional.ofNullable(empleadoRepository.findByEmpleado(id));
    }

}
