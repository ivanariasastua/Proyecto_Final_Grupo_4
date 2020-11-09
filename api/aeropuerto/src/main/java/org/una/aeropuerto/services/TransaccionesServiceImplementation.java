/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

import java.util.Date;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.una.aeropuerto.dto.TransaccionesDTO;
import org.una.aeropuerto.entities.Transacciones;
import org.una.aeropuerto.repositories.ITransaccionesRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class TransaccionesServiceImplementation implements ITransaccionesService {

    @Autowired
    private ITransaccionesRepository transRepository;

    @Override
    @Transactional
    public TransaccionesDTO create(TransaccionesDTO transacciones) {
        Transacciones trans = MapperUtils.EntityFromDto(transacciones, Transacciones.class);
        trans = transRepository.save(trans);
        return MapperUtils.DtoFromEntity(trans, TransaccionesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionesDTO>> findByAccion(String accion) {
        return ServiceConvertionHelper.findList(transRepository.findByAccion(accion), TransaccionesDTO.class);
    }

    @Override
    public Optional<List<TransaccionesDTO>> findByFechas(Date fechaInicio, Date fechaFinal) {
        return ServiceConvertionHelper.findList(transRepository.findByFechaRegistroBetween(fechaInicio, fechaFinal), TransaccionesDTO.class);
    }

}
