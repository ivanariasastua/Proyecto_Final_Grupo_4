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
import org.una.aeropuerto.entities.Empleados;
import org.una.aeropuerto.repositories.IEmpleadosRepository;

/**
 *
 * @author cordo
 */
@Service
public class EmpleadosServiceImplementation implements IEmpleadosService {

    @Autowired
    private IEmpleadosRepository empleadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Empleados> findById(Long id) {
        return empleadoRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Empleados>> findAll() {
        return Optional.ofNullable(empleadoRepository.findAll());
    }

    @Override
    @Transactional
    public Empleados create(Empleados empleado) {
        return empleadoRepository.save(empleado);
    }

    @Override
    public Optional<Empleados> update(Empleados empleado, Long id) {
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
    public Optional<List<Empleados>> findByUsuariosId(Long id) {
        return Optional.ofNullable(empleadoRepository.findByUsuariosId(id));
    }

    @Override
    public Optional<List<Empleados>> findByNombre(String nombre) {

        return Optional.ofNullable(empleadoRepository.findByNombre(nombre));
    }

    @Override
    public Optional<List<Empleados>> findByCedula(String cedula) {
        return Optional.ofNullable(empleadoRepository.findByCedula(cedula));
    }

}
