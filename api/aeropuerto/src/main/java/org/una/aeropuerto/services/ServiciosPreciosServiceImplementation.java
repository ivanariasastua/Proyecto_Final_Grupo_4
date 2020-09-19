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
import org.una.aeropuerto.entities.ServiciosPrecios;
import org.una.aeropuerto.repositories.IServiciosPreciosRepository;

/**
 *
 * @author cordo
 */
@Service
public class ServiciosPreciosServiceImplementation implements IServiciosPreciosService {

    @Autowired
    private IServiciosPreciosRepository servRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServiciosPrecios>> findAll() {
        return Optional.ofNullable(servRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiciosPrecios> findById(Long id) {
        return servRepository.findById(id);
    }

    @Override
    @Transactional
    public ServiciosPrecios create(ServiciosPrecios serviciosPrecios) {
        return servRepository.save(serviciosPrecios);
    }

    @Override
    @Transactional
    public Optional<ServiciosPrecios> update(ServiciosPrecios serviciosPrecios, Long id) {
        if (servRepository.findById(id).isPresent()) {
            return Optional.ofNullable(servRepository.save(serviciosPrecios));
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        servRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        servRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServiciosPrecios>> findByServiciosId(Long id) {
        return Optional.ofNullable(servRepository.findByServicio(id));
    }
}
