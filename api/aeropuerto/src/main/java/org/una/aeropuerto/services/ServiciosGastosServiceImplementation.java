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
import org.una.aeropuerto.dto.ServiciosGastosDTO;
import org.una.aeropuerto.entities.ServiciosGastos;
import org.una.aeropuerto.repositories.IServiciosGastosRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class ServiciosGastosServiceImplementation implements IServiciosGastosService {

    @Autowired
    private IServiciosGastosRepository gastosRepository;

    @Override
    @Transactional
    public ServiciosGastosDTO create(ServiciosGastosDTO gastosMantenimientos) {
        ServiciosGastos gasto = MapperUtils.EntityFromDto(gastosMantenimientos, ServiciosGastos.class);
        gasto = gastosRepository.save(gasto);
        return MapperUtils.DtoFromEntity(gasto, ServiciosGastosDTO.class);
    }

    @Override
    @Transactional
    public Optional<ServiciosGastosDTO> update(ServiciosGastosDTO gastosMantenimientos, Long id) {
        if (gastosRepository.findById(id).isPresent()) {
            ServiciosGastos gasto = MapperUtils.EntityFromDto(gastosMantenimientos, ServiciosGastos.class);
            gasto = gastosRepository.save(gasto);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(gasto, ServiciosGastosDTO.class));
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServiciosGastosDTO>> findByServicios(String servicio) {
        return ServiceConvertionHelper.findList(gastosRepository.findByServicio(servicio), ServiciosGastosDTO.class);
    }

    @Override
    public Optional<List<ServiciosGastosDTO>> findByEmpresa(String empresa) {
        return ServiceConvertionHelper.findList(gastosRepository.findByEmpresa(empresa), ServiciosGastosDTO.class);
    }

    @Override
    public Optional<List<ServiciosGastosDTO>> findByContrato(String empresa) {
        return ServiceConvertionHelper.findList(gastosRepository.findByContrato(empresa), ServiciosGastosDTO.class);
    }


}
