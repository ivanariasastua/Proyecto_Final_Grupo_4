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
import org.una.aeropuerto.entities.Servicios;
import org.una.aeropuerto.repositories.IServiciosRepository;

/**
 *
 * @author cordo
 */
@Service
public class ServiciosServiceImplementation implements IServiciosService {

    @Autowired
    private IServiciosRepository serviciosRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Servicios>> findAll() {
        return Optional.ofNullable(serviciosRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Servicios> findById(Long id) {
        return serviciosRepository.findById(id);
    }

    @Override
    @Transactional
    public Servicios create(Servicios servicios) {
        return serviciosRepository.save(servicios);
    }

    @Override
    @Transactional
    public Optional<Servicios> update(Servicios servicios, Long id) {
        if (serviciosRepository.findById(id).isPresent()) {
            return Optional.ofNullable(serviciosRepository.save(servicios));
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        serviciosRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        serviciosRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Servicios>> findByNombre(String nombre) {
        return Optional.ofNullable(serviciosRepository.findByNombre(nombre));
    }

}
