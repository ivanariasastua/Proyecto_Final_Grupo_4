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
import org.una.aeropuerto.dto.EmpleadosAreasTrabajosDTO;
import org.una.aeropuerto.entities.EmpleadosAreasTrabajos;
import org.una.aeropuerto.utils.*;
import org.una.aeropuerto.repositories.IEmpleadosAreasTrabajosRepository;

/**
 *
 * @author cordo
 */
@Service
public class EmpleadosAreasTrabajosServiceImplementation implements IEmpleadosAreasTrabajosService {

    @Autowired
    private IEmpleadosAreasTrabajosRepository empleadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadosAreasTrabajosDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(empleadoRepository.findById(id), EmpleadosAreasTrabajosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadosAreasTrabajosDTO>> findAll() {
        return ServiceConvertionHelper.findList(empleadoRepository.findAll(), EmpleadosAreasTrabajosDTO.class);
    }

    @Override
    @Transactional
    public EmpleadosAreasTrabajosDTO create(EmpleadosAreasTrabajosDTO areadto) {
        EmpleadosAreasTrabajos area = MapperUtils.EntityFromDto(areadto, EmpleadosAreasTrabajos.class);
        area = empleadoRepository.save(area);
        return MapperUtils.DtoFromEntity(area, EmpleadosAreasTrabajosDTO.class);
    }

    @Override
    @Transactional
    public Optional<EmpleadosAreasTrabajosDTO> update(EmpleadosAreasTrabajosDTO areadto, Long id) {
        if (empleadoRepository.findById(id).isPresent()) {
            EmpleadosAreasTrabajos area = MapperUtils.EntityFromDto(areadto, EmpleadosAreasTrabajos.class);
            area = empleadoRepository.save(area);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(area, EmpleadosAreasTrabajosDTO.class));
        }
        return null;   
    }

    @Override
    @Transactional
    public Optional<EmpleadosAreasTrabajosDTO> inactivate(Long id) {
        empleadoRepository.inactivar(id);
        return ServiceConvertionHelper.oneToOptionalDto(empleadoRepository.findById(id), EmpleadosAreasTrabajosDTO.class);
    }

}
