/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.Transacciones;

/**
 *
 * @author cordo
 */
public interface ITransaccionesService {
    
    public Optional<List<Transacciones>> findAll();

    public Optional<Transacciones> findById(Long id);
    
    public Transacciones create(Transacciones transacciones);

    public Optional<Transacciones> update(Transacciones transacciones, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<Transacciones>> findByAccion(String accion);
    
    public Optional<List<Transacciones>> findByUsuariosId(Long id);
}
