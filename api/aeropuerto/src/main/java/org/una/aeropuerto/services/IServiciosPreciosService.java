/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.ServiciosPreciosDTO;
import org.una.aeropuerto.entities.ServiciosPrecios;

/**
 *
 * @author cordo
 */
public interface IServiciosPreciosService {
    
    public Optional<List<ServiciosPreciosDTO>> findAll();

    public Optional<ServiciosPreciosDTO> findById(Long id);

    public ServiciosPreciosDTO create(ServiciosPreciosDTO serviciosPrecios);

    public Optional<ServiciosPreciosDTO> update(ServiciosPreciosDTO serviciosPrecios, Long id);
    
}
