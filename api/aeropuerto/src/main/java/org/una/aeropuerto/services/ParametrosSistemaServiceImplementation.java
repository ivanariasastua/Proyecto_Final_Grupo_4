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
import org.una.aeropuerto.entities.ParametrosSistema;
import org.una.aeropuerto.repositories.IParametrosSistemaRepository;

/**
 *
 * @author cordo
 */
@Service
public class ParametrosSistemaServiceImplementation implements IParametrosSistemaService {

    @Autowired
    private IParametrosSistemaRepository parametrosRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosSistema>> findAll() {
        return Optional.ofNullable(parametrosRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ParametrosSistema> findById(Long id) {
        return parametrosRepository.findById(id);
    }

    @Override
    @Transactional
    public ParametrosSistema create(ParametrosSistema parametrosSistema) {
        return parametrosRepository.save(parametrosSistema);
    }

    @Override
    @Transactional
    public Optional<ParametrosSistema> update(ParametrosSistema parametrosSistema, Long id) {
        if (parametrosRepository.findById(id).isPresent()) {
            return Optional.ofNullable(parametrosRepository.save(parametrosSistema));
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        parametrosRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        parametrosRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ParametrosSistema>> findByValor(String valor) {
        return Optional.ofNullable(parametrosRepository.findByValor(valor));
    }
}
