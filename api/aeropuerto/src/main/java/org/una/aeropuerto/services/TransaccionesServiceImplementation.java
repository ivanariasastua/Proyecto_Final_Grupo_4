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
import org.una.aeropuerto.entities.Transacciones;
import org.una.aeropuerto.repositories.ITransaccionesRepository;

/**
 *
 * @author cordo
 */
@Service
public class TransaccionesServiceImplementation implements ITransaccionesService{

    @Autowired
    private ITransaccionesRepository transRepository;
            
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Transacciones>> findAll() {
        return Optional.ofNullable(transRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Transacciones> findById(Long id) {
        return transRepository.findById(id);
    }

    @Override
    @Transactional
    public Transacciones create(Transacciones transacciones) {
        return transRepository.save(transacciones);
    }

    @Override
    @Transactional
    public Optional<Transacciones> update(Transacciones transacciones, Long id) {
        if (transRepository.findById(id).isPresent()) {
            return Optional.ofNullable(transRepository.save(transacciones));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        transRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        transRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Transacciones>> findByAccion(String accion) {
        return Optional.ofNullable(transRepository.findByAccion(accion));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Transacciones>> findByUsuariosId(Long id) {
        return  Optional.ofNullable(transRepository.findByUsuariosId(id));
    }
    
}
