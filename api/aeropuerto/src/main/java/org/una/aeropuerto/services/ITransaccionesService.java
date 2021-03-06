/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.una.aeropuerto.dto.TransaccionesDTO;

/**
 *
 * @author cordo
 */
public interface ITransaccionesService {
    
    public TransaccionesDTO create(TransaccionesDTO transacciones);

    public Optional<List<TransaccionesDTO>> findByAccion(String accion);
    
    public Optional<List<TransaccionesDTO>> findByFechas(Date fechaInicio, Date fechaFinal);
    
    public Optional<List<TransaccionesDTO>> filtro(String empleado, Date fechaInicio, Date fechaFinal);
}
