/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.TransaccionesDTO;
import org.una.aeropuerto.entities.Transacciones;

/**
 *
 * @author cordo
 */
public interface ITransaccionesService {
    
    public Optional<List<TransaccionesDTO>> findAll();

    public Optional<TransaccionesDTO> findById(Long id);
    
    public TransaccionesDTO create(TransaccionesDTO transacciones);

    public Optional<TransaccionesDTO> update(TransaccionesDTO transacciones, Long id);
    
    public Optional<List<TransaccionesDTO>> findByAccion(String accion);
    
    public Optional<List<TransaccionesDTO>> findByUsuariosId(Long id);
    
    public Optional<TransaccionesDTO> inactivate(Long id);
}
