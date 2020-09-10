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
import org.una.aeropuerto.entities.GastosMantenimientos;
import org.una.aeropuerto.repositories.IGastosMantinimientosRepository;

/**
 *
 * @author cordo
 */
@Service
public class GastosMantenimientosServiceImplementation implements IGastosMantenimientosService {

    @Autowired
    IGastosMantinimientosRepository gastosRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<GastosMantenimientos>> findAll() {
        return Optional.ofNullable(gastosRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<GastosMantenimientos> findById(Long id) {
        return gastosRepository.findById(id);
    }

    @Override
    @Transactional
    public GastosMantenimientos create(GastosMantenimientos gastosMantenimientos) {
        return gastosRepository.save(gastosMantenimientos);
    }

    @Override
    @Transactional
    public Optional<GastosMantenimientos> update(GastosMantenimientos gastosMantenimientos, Long id) {
        if (gastosRepository.findById(id).isPresent()) {
            return Optional.ofNullable(gastosRepository.save(gastosMantenimientos));
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        gastosRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        gastosRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<GastosMantenimientos>> findByServiciosId(Long id) {
        return Optional.ofNullable(gastosRepository.findByServiciosId(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<GastosMantenimientos>> findByEmpresa(String empresa) {
        return Optional.ofNullable(gastosRepository.findByEmpresa(empresa));
    }
}
