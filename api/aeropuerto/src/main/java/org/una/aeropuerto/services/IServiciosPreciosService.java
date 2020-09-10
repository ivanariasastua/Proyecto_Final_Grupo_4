/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.ServiciosPrecios;

/**
 *
 * @author cordo
 */
public interface IServiciosPreciosService {
    
    public Optional<List<ServiciosPrecios>> findAll();

    public Optional<ServiciosPrecios> findById(Long id);

    public ServiciosPrecios create(ServiciosPrecios serviciosPrecios);

    public Optional<ServiciosPrecios> update(ServiciosPrecios serviciosPrecios, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<ServiciosPrecios>> findByServiciosId(Long id);
}
