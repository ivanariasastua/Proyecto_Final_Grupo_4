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
import org.una.aeropuerto.dto.ServiciosDTO;
import org.una.aeropuerto.entities.Servicios;
import org.una.aeropuerto.repositories.IServiciosRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class ServiciosServiceImplementation implements IServiciosService {

    @Autowired
    private IServiciosRepository serviciosRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiciosDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(serviciosRepository.findById(id), ServiciosDTO.class);
    }

    @Override
    @Transactional
    public ServiciosDTO create(ServiciosDTO servicios) {
        Servicios serv = MapperUtils.EntityFromDto(servicios, Servicios.class);
        serv = serviciosRepository.save(serv);
        return MapperUtils.DtoFromEntity(serv, ServiciosDTO.class);
    }

    @Override
    @Transactional
    public Optional<ServiciosDTO> update(ServiciosDTO servicios, Long id) {
        if (serviciosRepository.findById(id).isPresent()) {
            Servicios serv = MapperUtils.EntityFromDto(servicios, Servicios.class);
            serv = serviciosRepository.save(serv);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(serv, ServiciosDTO.class));
        } else {
            return null;
        }
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServiciosDTO>> findByNombre(String nombre) {
        return ServiceConvertionHelper.findList(serviciosRepository.findByNombre(nombre), ServiciosDTO.class);
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServiciosDTO>> findByEstado(boolean estado) {
        return ServiceConvertionHelper.findList(serviciosRepository.findByEstado(estado), ServiciosDTO.class);
    }

}
