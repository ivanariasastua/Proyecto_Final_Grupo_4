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
import org.una.aeropuerto.entities.Usuarios;
import org.una.aeropuerto.repositories.IUsuariosRepository;

/**
 *
 * @author cordo
 */
@Service
public class UsuariosServiceImplementation implements IUsuariosService {

    @Autowired
    private IUsuariosRepository usuarioRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<Usuarios>> findAll() {
        return Optional.ofNullable(usuarioRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<Usuarios> findById(Long id) {
        return usuarioRepository.findById(id);
    }

    @Override
    @Transactional
    public Usuarios create(Usuarios usuario) {
        return usuarioRepository.save(usuario);
    }

    @Override
    @Transactional
    public Optional<Usuarios> update(Usuarios usuario, Long id) {
        if (usuarioRepository.findById(id).isPresent()) {
            return Optional.ofNullable(usuarioRepository.save(usuario));
        } else {
            return null;
        }

    }

    @Override
    @Transactional
    public void delete(Long id) {
        usuarioRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        usuarioRepository.deleteAll();
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<Usuarios>> findUsersByRolesId(Long id) {
        return Optional.ofNullable(usuarioRepository.findByRol(id));
    }
}
