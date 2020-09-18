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
import org.una.aeropuerto.entities.EmpleadosAreasTrabajos;
import org.una.aeropuerto.repositories.IEmpleadosAreasTrabajosRepository;

/**
 *
 * @author cordo
 */
@Service
public class EmpleadosAreasTrabajosServiceImplementation implements IEmpleadosAreasTrabajosService {

    @Autowired
    private IEmpleadosAreasTrabajosRepository empleadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadosAreasTrabajos> findById(Long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadosAreasTrabajos>> findAll() {
        return Optional.ofNullable(empleadoRepository.findAll());
    }

    @Override
    @Transactional
    public EmpleadosAreasTrabajos create(EmpleadosAreasTrabajos departamento) {
        return empleadoRepository.save(departamento);
    }

    @Override
    public Optional<EmpleadosAreasTrabajos> update(EmpleadosAreasTrabajos departamento, Long id) {
        if (empleadoRepository.findById(id).isPresent()) {
            return Optional.ofNullable(empleadoRepository.saveAndFlush(departamento));
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
    public Optional<List<EmpleadosAreasTrabajos>> findByEmpleadoId(Long id) {
        return Optional.ofNullable(empleadoRepository.findByEmpleadosId(id));
    }

    @Override
    public Optional<List<EmpleadosAreasTrabajos>> findByAreaTrabajoId(Long id) {
        return Optional.ofNullable(empleadoRepository.findByAreaTrabjoId(id));
    }

}
