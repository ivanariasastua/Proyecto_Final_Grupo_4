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
import org.una.aeropuerto.dto.ServiciosPreciosDTO;
import org.una.aeropuerto.entities.ServiciosPrecios;
import org.una.aeropuerto.repositories.IServiciosPreciosRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class ServiciosPreciosServiceImplementation implements IServiciosPreciosService {

    @Autowired
    private IServiciosPreciosRepository servRepository;

    @Override
    @Transactional
    public ServiciosPreciosDTO create(ServiciosPreciosDTO serviciosPrecios) {
        ServiciosPrecios serv = MapperUtils.EntityFromDto(serviciosPrecios, ServiciosPrecios.class);
        serv = servRepository.save(serv);
        return MapperUtils.DtoFromEntity(serv, ServiciosPreciosDTO.class);
    }

    @Override
    @Transactional
    public Optional<ServiciosPreciosDTO> update(ServiciosPreciosDTO serviciosPrecios, Long id) {
        if (servRepository.findById(id).isPresent()) {
            ServiciosPrecios serv = MapperUtils.EntityFromDto(serviciosPrecios, ServiciosPrecios.class);
            serv = servRepository.save(serv);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(serv, ServiciosPreciosDTO.class));
        } else {
            return null;
        }
    }

}
