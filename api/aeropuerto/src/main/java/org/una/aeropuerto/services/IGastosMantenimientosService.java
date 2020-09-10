/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.GastosMantenimientos;

/**
 *
 * @author cordo
 */
public interface IGastosMantenimientosService {
    public Optional<List<GastosMantenimientos>> findAll();

    public Optional<GastosMantenimientos> findById(Long id);
    
    public GastosMantenimientos create(GastosMantenimientos gastosMantenimientos);

    public Optional<GastosMantenimientos> update(GastosMantenimientos gastosMantenimientos, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<GastosMantenimientos>> findByServiciosId(Long id);
    
    public Optional<List<GastosMantenimientos>> findByEmpresa(String empresa);
}
