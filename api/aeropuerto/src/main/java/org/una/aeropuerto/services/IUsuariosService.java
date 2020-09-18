/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Usuarios;

/**
 *
 * @author cordo
 */
public interface IUsuariosService {
    public Optional<List<Usuarios>> findAll();

    public Optional<Usuarios> findById(Long id);
    
    public Usuarios create(Usuarios usuario);

    public Optional<Usuarios> update(Usuarios usuario, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<Usuarios>> findUsersByRolesId(Long id);

    
}
