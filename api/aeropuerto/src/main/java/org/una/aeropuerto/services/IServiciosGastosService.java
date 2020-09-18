/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.ServiciosGastos;

/**
 *
 * @author cordo
 */
public interface IServiciosGastosService {
    public Optional<List<ServiciosGastos>> findAll();

    public Optional<ServiciosGastos> findById(Long id);
    
    public ServiciosGastos create(ServiciosGastos gastosMantenimientos);

    public Optional<ServiciosGastos> update(ServiciosGastos gastosMantenimientos, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<ServiciosGastos>> findByServiciosId(Long id);
    
    public Optional<List<ServiciosGastos>> findByEmpresa(String empresa);
    
    public Optional<List<ServiciosGastos>> findByNumeroContrato(String numeroContrato);
}
