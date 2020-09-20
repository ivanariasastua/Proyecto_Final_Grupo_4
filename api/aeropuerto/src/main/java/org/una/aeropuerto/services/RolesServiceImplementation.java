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
import org.una.aeropuerto.dto.RolesDTO;
import org.una.aeropuerto.entities.Roles;
import org.una.aeropuerto.repositories.IRolesRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class RolesServiceImplementation implements IRolesService {

    @Autowired
    private IRolesRepository rolRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolesDTO>> findAll() {
        return ServiceConvertionHelper.findList(rolRepository.findAll(), RolesDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<RolesDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(rolRepository.findById(id), RolesDTO.class);
    }

    @Override
    @Transactional
    public RolesDTO create(RolesDTO rol) {
        Roles entidad = MapperUtils.EntityFromDto(rol, Roles.class);
        entidad = rolRepository.save(entidad);
        return MapperUtils.DtoFromEntity(entidad, RolesDTO.class);
    }

    @Override
    @Transactional
    public Optional<RolesDTO> update(RolesDTO rol, Long id) {
        if(rolRepository.findById(id).isPresent()){
            Roles entity = MapperUtils.EntityFromDto(rol, Roles.class);
            entity = rolRepository.save(entity);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(entity, RolesDTO.class));
        }
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<RolesDTO>> findByNombre(String nombre) {
        return ServiceConvertionHelper.findList(rolRepository.findByNombre(nombre), RolesDTO.class);
    }
}
