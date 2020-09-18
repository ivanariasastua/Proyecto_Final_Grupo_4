/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Roles;

/**
 *
 * @author cordo
 */
public interface IRolesService {
    
    public Optional<List<Roles>> findAll();

    public Optional<Roles> findById(Long id);
    
    public Roles create(Roles rol);

    public Optional<Roles> update(Roles rol, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<Roles>> findByNombre(String nombre);
}
