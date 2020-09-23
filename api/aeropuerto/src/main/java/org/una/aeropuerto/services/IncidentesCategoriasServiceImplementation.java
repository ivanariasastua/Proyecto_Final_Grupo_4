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
import org.una.aeropuerto.dto.IncidentesCategoriasDTO;
import org.una.aeropuerto.entities.IncidentesCategorias;
import org.una.aeropuerto.repositories.IIncidentesCategoriasRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class IncidentesCategoriasServiceImplementation implements IIncidentesCategoriasService{
    
    @Autowired
    private IIncidentesCategoriasRepository incidenteReppository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<IncidentesCategoriasDTO>> findAll() {
        return ServiceConvertionHelper.findList(incidenteReppository.findAll(), IncidentesCategoriasDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IncidentesCategoriasDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(incidenteReppository.findById(id), IncidentesCategoriasDTO.class);
    }

    @Override
    @Transactional
    public IncidentesCategoriasDTO create(IncidentesCategoriasDTO incidentesCategorias) {
        IncidentesCategorias entidad = MapperUtils.EntityFromDto(incidentesCategorias, IncidentesCategorias.class);
        entidad = incidenteReppository.save(entidad);
        return MapperUtils.DtoFromEntity(entidad, IncidentesCategoriasDTO.class);
    }

    @Override
    @Transactional
    public Optional<IncidentesCategoriasDTO> update(IncidentesCategoriasDTO incidentesCategorias, Long id) {
        if(incidenteReppository.findById(id).isPresent()){
            IncidentesCategorias entidad = MapperUtils.EntityFromDto(incidentesCategorias, IncidentesCategorias.class);
            entidad = incidenteReppository.save(entidad);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(entidad, IncidentesCategoriasDTO.class));
        }else{
            return null;
        }
    
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<IncidentesCategoriasDTO>> findByNombre(String nombre) {
        return ServiceConvertionHelper.findList(incidenteReppository.findByNombre(nombre), IncidentesCategoriasDTO.class);
    }
    
    
}
