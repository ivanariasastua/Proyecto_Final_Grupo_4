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
import org.una.aeropuerto.entities.Notas;
import org.una.aeropuerto.repositories.INotasRepository;

/**
 *
 * @author cordo
 */
@Service
public class NotasServiceImplementation implements INotasService {

    @Autowired
    private INotasRepository notasRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Notas>> findAll() {
        return Optional.ofNullable(notasRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Notas> findById(Long id) {
        return notasRepository.findById(id);
    }

    @Override
    @Transactional
    public Notas create(Notas notas) {
        return notasRepository.save(notas);
    }

    @Override
    @Transactional
    public Optional<Notas> update(Notas notas, Long id) {
        if (notasRepository.findById(id).isPresent()) {
            return Optional.ofNullable(notasRepository.save(notas));
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        notasRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        notasRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Notas>> findByGastosMantenimientosId(Long id) {
        return Optional.ofNullable(notasRepository.findByGastosMantenimientosId(id));
    }

}
