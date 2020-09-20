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
import org.una.aeropuerto.dto.AreasTrabajosDTO;
import org.una.aeropuerto.entities.AreasTrabajos;
import org.una.aeropuerto.repositories.IAreasTrabajosRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class AreasTrabajosServiceImplementation implements IAreasTrabajosService {

    @Autowired
    private IAreasTrabajosRepository areasRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<AreasTrabajosDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(areasRepository.findById(id), AreasTrabajosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreasTrabajosDTO>> findAll() {
        return ServiceConvertionHelper.findList(areasRepository.findAll(), AreasTrabajosDTO.class);
    }

    @Override
    @Transactional
    public AreasTrabajosDTO create(AreasTrabajosDTO area) {
        AreasTrabajos ent = MapperUtils.EntityFromDto(area, AreasTrabajos.class);
        ent = areasRepository.save(ent);
        return MapperUtils.DtoFromEntity(ent, AreasTrabajosDTO.class);
    }

    @Override
    @Transactional
    public Optional<AreasTrabajosDTO> update(AreasTrabajosDTO area, Long id) {
        if (areasRepository.findById(id).isPresent()) {
            AreasTrabajos ent = MapperUtils.EntityFromDto(area, AreasTrabajos.class);
            ent = areasRepository.save(ent);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(ent, AreasTrabajosDTO.class));
        } 
        return null;
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<AreasTrabajosDTO>> findByNombre(String nombre) {
        return ServiceConvertionHelper.findList(areasRepository.findByNombre(nombre), AreasTrabajosDTO.class);
    }

    @Override
    @Transactional
    public Optional<AreasTrabajosDTO> inactivate(Long id) {
        areasRepository.inactivar(id);
        return ServiceConvertionHelper.oneToOptionalDto(areasRepository.findById(id), AreasTrabajosDTO.class);
    }


}
