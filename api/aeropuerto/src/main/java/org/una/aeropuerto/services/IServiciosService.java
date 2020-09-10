/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Servicios;

/**
 *
 * @author cordo
 */
public interface IServiciosService {
    public Optional<List<Servicios>> findAll();

    public Optional<Servicios> findById(Long id);
    
    public Servicios create(Servicios servicios);

    public Optional<Servicios> update(Servicios servicios, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<Servicios>> findByNombre(String nombre);
}
