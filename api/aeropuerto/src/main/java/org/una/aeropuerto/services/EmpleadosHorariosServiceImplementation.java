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
import org.una.aeropuerto.dto.EmpleadosHorariosDTO;
import org.una.aeropuerto.entities.EmpleadosHorarios;
import org.una.aeropuerto.utils.*;
import org.una.aeropuerto.repositories.IEmpleadosHorariosRepository;

/**
 *
 * @author cordo
 */
@Service
public class EmpleadosHorariosServiceImplementation implements IEmpleadosHorariosService {

    @Autowired
    private IEmpleadosHorariosRepository empleadoRepository;

    @Override
    @Transactional
    public EmpleadosHorariosDTO create(EmpleadosHorariosDTO horarioDto) {
        EmpleadosHorarios horario = MapperUtils.EntityFromDto(horarioDto, EmpleadosHorarios.class);
        horario = empleadoRepository.save(horario);
        return MapperUtils.DtoFromEntity(horario, EmpleadosHorariosDTO.class);
    }

    @Override
    @Transactional
    public Optional<EmpleadosHorariosDTO> update(EmpleadosHorariosDTO horarioDto, Long id) {
        if (empleadoRepository.findById(id).isPresent()) {
            EmpleadosHorarios horario = MapperUtils.EntityFromDto(horarioDto, EmpleadosHorarios.class);
            horario = empleadoRepository.save(horario);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(horario, EmpleadosHorariosDTO.class));
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadosHorariosDTO>> findByEmpleadoId(Long id) {
        return Optional.ofNullable(MapperUtils.DtoListFromEntityList(empleadoRepository.getHorariosMarcajes(id), EmpleadosHorariosDTO.class));
    }
}
