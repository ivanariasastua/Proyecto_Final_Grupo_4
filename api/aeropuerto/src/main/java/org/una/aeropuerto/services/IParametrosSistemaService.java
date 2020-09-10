/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.entities.ParametrosSistema;

/**
 *
 * @author cordo
 */
public interface IParametrosSistemaService {
    
    public Optional<List<ParametrosSistema>> findAll();

    public Optional<ParametrosSistema> findById(Long id);

    public ParametrosSistema create(ParametrosSistema parametrosSistema);

    public Optional<ParametrosSistema> update(ParametrosSistema parametrosSistema, Long id);

    public void delete(Long id);

    public void deleteAll();
    
    public Optional<List<ParametrosSistema>> findByValor(String valor);
}
