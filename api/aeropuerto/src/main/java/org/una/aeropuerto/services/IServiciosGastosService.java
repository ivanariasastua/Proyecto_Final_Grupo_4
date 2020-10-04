/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.ServiciosGastosDTO;

/**
 *
 * @author cordo
 */
public interface IServiciosGastosService {
    public Optional<List<ServiciosGastosDTO>> findAll();

    public Optional<ServiciosGastosDTO> findById(Long id);
    
    public ServiciosGastosDTO create(ServiciosGastosDTO gastosMantenimientos);

    public Optional<ServiciosGastosDTO> update(ServiciosGastosDTO gastosMantenimientos, Long id);
    
    public Optional<List<ServiciosGastosDTO>> findByServicios(String servicio);
    
    public Optional<List<ServiciosGastosDTO>> findByEmpresa(String empresa);
    
    public Optional<List<ServiciosGastosDTO>> findByContrato(String contrato);
    

}
