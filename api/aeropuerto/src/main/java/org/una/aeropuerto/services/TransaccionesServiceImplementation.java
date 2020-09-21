/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.services;

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
    @Transactional(readOnly = true)
    public Optional<List<TransaccionesDTO>> findAll() {
        return ServiceConvertionHelper.findList(transRepository.findAll(), TransaccionesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<TransaccionesDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(transRepository.findById(id), TransaccionesDTO.class);
    }

    @Override
    @Transactional
    public TransaccionesDTO create(TransaccionesDTO transacciones) {
        Transacciones trans = MapperUtils.EntityFromDto(transacciones, Transacciones.class);
        trans = transRepository.save(trans);
        return MapperUtils.DtoFromEntity(trans, TransaccionesDTO.class);
    }

    @Override
    @Transactional
    public Optional<TransaccionesDTO> update(TransaccionesDTO transacciones, Long id) {
        if (transRepository.findById(id).isPresent()) {
            Transacciones trans = MapperUtils.EntityFromDto(transacciones, Transacciones.class);
            trans = transRepository.save(trans);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(trans, TransaccionesDTO.class));
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<TransaccionesDTO>> findByAccion(String accion) {
        return ServiceConvertionHelper.findList(transRepository.findByAccion(accion), TransaccionesDTO.class);
    }

    @Override
    public Optional<TransaccionesDTO> inactivate(Long id) {
        transRepository.inactivar(id);
        return ServiceConvertionHelper.oneToOptionalDto(transRepository.findById(id), TransaccionesDTO.class);
    }

}
