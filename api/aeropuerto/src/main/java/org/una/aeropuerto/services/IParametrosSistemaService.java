/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.ParametrosSistemaDTO;
import org.una.aeropuerto.entities.ParametrosSistema;

/**
 *
 * @author cordo
 */
public interface IParametrosSistemaService {
    
    public Optional<List<ParametrosSistemaDTO>> findAll();

    public Optional<ParametrosSistemaDTO> findById(Long id);

    public ParametrosSistemaDTO create(ParametrosSistemaDTO parametrosSistema);

    public Optional<ParametrosSistemaDTO> update(ParametrosSistemaDTO parametrosSistema, Long id);

    public Optional<ParametrosSistemaDTO> inactivate(Long id);
    
    public Optional<List<ParametrosSistemaDTO>> findByValor(String valor);
}
