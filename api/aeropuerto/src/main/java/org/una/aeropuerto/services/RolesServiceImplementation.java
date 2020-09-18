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
import org.una.aeropuerto.entities.Roles;
import org.una.aeropuerto.repositories.IRolesRepository;

/**
 *
 * @author cordo
 */
@Service
public class RolesServiceImplementation implements IRolesService {

    @Autowired
    private IRolesRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<Roles> findById(Long id) {
        return rolRepository.findById(id);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Roles>> findAll() {
        return Optional.ofNullable(rolRepository.findAll());
    }

    @Override
    @Transactional
    public Roles create(Roles rol) {
        return rolRepository.save(rol);
    }

    @Override
    public Optional<Roles> update(Roles rol, Long id) {
        if (rolRepository.findById(id).isPresent()) {
            return Optional.ofNullable(rolRepository.saveAndFlush(rol));
        } else {
            return null;
        }
    }

    @Override
    public void delete(Long id) {
        rolRepository.deleteById(id);
    }

    @Override
    public void deleteAll() {
        rolRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Roles>> findByNombre(String nombre) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }
}
