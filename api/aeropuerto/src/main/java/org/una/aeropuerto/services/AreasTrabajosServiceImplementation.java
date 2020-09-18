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
import org.una.aeropuerto.entities.AreasTrabajos;
import org.una.aeropuerto.repositories.IAreasTrabajosRepository;

/**
 *
 * @author cordo
 */
@Service
public class AreasTrabajosServiceImplementation implements IAreasTrabajosService {

    @Autowired
    private IAreasTrabajosRepository areasRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<AreasTrabajos> findById(Long id) {
        return areasRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreasTrabajos>> findAll() {
        return Optional.ofNullable(areasRepository.findAll());
    }

    @Override
    @Transactional
    public AreasTrabajos create(AreasTrabajos area) {
        return areasRepository.save(area);
    }

    @Override
    public Optional<AreasTrabajos> update(AreasTrabajos area, Long id) {
        if (areasRepository.findById(id).isPresent()) {
            return Optional.ofNullable(areasRepository.saveAndFlush(area));
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        areasRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        areasRepository.deleteAll();
    }

    @Override
    public Optional<List<AreasTrabajos>> findByNombre(String nombre) {
        return Optional.ofNullable(areasRepository.findByNombre(nombre));
    }

}
