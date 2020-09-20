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
import org.una.aeropuerto.dto.EmpleadosDTO;
import org.una.aeropuerto.entities.Empleados;
import org.una.aeropuerto.repositories.IEmpleadosRepository;
import org.una.aeropuerto.utils.*;

/**
 *
 * @author cordo
 */
@Service
public class EmpleadosServiceImplementation implements IEmpleadosService {

    @Autowired
    private IEmpleadosRepository empleadoRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<EmpleadosDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(empleadoRepository.findById(id), EmpleadosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadosDTO>> findAll() {
        return ServiceConvertionHelper.findList(empleadoRepository.findAll(), EmpleadosDTO.class);
    }

    @Override
    @Transactional
    public EmpleadosDTO create(EmpleadosDTO empleado) {
        Empleados colab = MapperUtils.EntityFromDto(empleado, Empleados.class);
        colab = empleadoRepository.save(colab);
        return MapperUtils.DtoFromEntity(colab, EmpleadosDTO.class);
    }

    @Override
    @Transactional
    public Optional<EmpleadosDTO> update(EmpleadosDTO empleado, Long id) {
        if (empleadoRepository.findById(id).isPresent()) {
            Empleados colab = MapperUtils.EntityFromDto(empleado, Empleados.class);
            colab = empleadoRepository.save(colab);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(colab, EmpleadosDTO.class));
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<EmpleadosDTO>> filtro(String nombre, String cedula, boolean estado, String area) {
        return ServiceConvertionHelper.findList(empleadoRepository.filtro(nombre, cedula, estado, area), EmpleadosDTO.class);
    }

    @Override
    @Transactional
    public Optional<EmpleadosDTO> inactivate(Long id) {
        empleadoRepository.inactivar(id);
        return ServiceConvertionHelper.oneToOptionalDto(empleadoRepository.findById(id), EmpleadosDTO.class);
    }

}
