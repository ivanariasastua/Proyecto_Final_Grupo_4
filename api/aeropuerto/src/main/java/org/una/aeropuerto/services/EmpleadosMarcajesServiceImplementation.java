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
import org.una.aeropuerto.dto.EmpleadosMarcajesDTO;
import org.una.aeropuerto.entities.EmpleadosMarcajes;
import org.una.aeropuerto.utils.*;
import org.una.aeropuerto.repositories.IEmpleadosMarcajesRepository;

/**
 *
 * @author cordo
 */
@Service
public class EmpleadosMarcajesServiceImplementation implements IEmpleadosMarcajesService{

    @Autowired
    private IEmpleadosMarcajesRepository empleadoRepository;
    
    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadosMarcajesDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(empleadoRepository.findById(id), EmpleadosMarcajesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadosMarcajesDTO>> findAll() {
        return ServiceConvertionHelper.findList(empleadoRepository.findAll(), EmpleadosMarcajesDTO.class);
    }

    @Override
    @Transactional
    public EmpleadosMarcajesDTO create(EmpleadosMarcajesDTO empleado) {
        EmpleadosMarcajes marcaje = MapperUtils.EntityFromDto(empleado, EmpleadosMarcajes.class);
        marcaje = empleadoRepository.save(marcaje);
        return MapperUtils.DtoFromEntity(marcaje, EmpleadosMarcajesDTO.class);
    }

    @Override
    @Transactional
    public Optional<EmpleadosMarcajesDTO> update(EmpleadosMarcajesDTO empleado, Long id) {
        if(empleadoRepository.findById(id).isPresent()){
            EmpleadosMarcajes marcaje = MapperUtils.EntityFromDto(empleado, EmpleadosMarcajes.class);
            marcaje = empleadoRepository.save(marcaje);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(marcaje, EmpleadosMarcajesDTO.class));
        }
        return null;
    }

    @Override
    public Optional<EmpleadosMarcajesDTO> findLastByHorarioId(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(Optional.ofNullable(empleadoRepository.findUltimoMarcaje(id).get().get(0)), EmpleadosMarcajesDTO.class);
    }
    
}
