/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.ParametrosSistemaDTO;

/**
 *
 * @author cordo
 */
public interface IParametrosSistemaService {

    public Optional<ParametrosSistemaDTO> findById(Long id);

    public ParametrosSistemaDTO create(ParametrosSistemaDTO parametrosSistema);

    public Optional<ParametrosSistemaDTO> update(ParametrosSistemaDTO parametrosSistema, Long id);

    public Optional<ParametrosSistemaDTO> findByValor(String valor);
    
    public Optional<ParametrosSistemaDTO> findByCodigoIdentificador(String codigo);
    
    public Optional<List<ParametrosSistemaDTO>> findByFechaRegistro(Date fecha1, Date fecha2);
    
    public Optional<List<ParametrosSistemaDTO>> findByFechaModificacion(Date fecha1, Date fech2);
}
