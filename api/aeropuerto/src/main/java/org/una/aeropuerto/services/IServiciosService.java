/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.ServiciosDTO;

/**
 *
 * @author cordo
 */
public interface IServiciosService {
    
    public Optional<ServiciosDTO> findById(Long id);

    public ServiciosDTO create(ServiciosDTO servicios);

    public Optional<ServiciosDTO> update(ServiciosDTO servicios, Long id);
    
    public Optional<List<ServiciosDTO>> findByNombre(String nombre);
    
    public Optional<List<ServiciosDTO>> findByEstado(boolean estado);
}
