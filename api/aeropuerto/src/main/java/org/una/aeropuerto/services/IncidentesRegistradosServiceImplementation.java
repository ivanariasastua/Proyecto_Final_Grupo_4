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
import org.una.aeropuerto.dto.IncidentesRegistradosDTO;
import org.una.aeropuerto.entities.IncidentesRegistrados;
import org.una.aeropuerto.repositories.IIncidentesRegistradosRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class IncidentesRegistradosServiceImplementation implements IIncidentesRegistradosService {

    @Autowired
    private IIncidentesRegistradosRepository incidenteReppository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<IncidentesRegistradosDTO>> findAll() {
        return ServiceConvertionHelper.findList(incidenteReppository.findAll(), IncidentesRegistradosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IncidentesRegistradosDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(incidenteReppository.findById(id), IncidentesRegistradosDTO.class);
    }

    @Override
    @Transactional
    public IncidentesRegistradosDTO create(IncidentesRegistradosDTO incidentesRegistrados) {
        IncidentesRegistrados entidad = MapperUtils.EntityFromDto(incidentesRegistrados, IncidentesRegistrados.class);
        entidad = incidenteReppository.save(entidad);
        return MapperUtils.DtoFromEntity(entidad, IncidentesRegistradosDTO.class);
    }

    @Override
    @Transactional
    public Optional<IncidentesRegistradosDTO> update(IncidentesRegistradosDTO incidentesRegistrados, Long id) {
        if(incidenteReppository.findById(id).isPresent()){
           IncidentesRegistrados entidad = MapperUtils.EntityFromDto(incidentesRegistrados, IncidentesRegistrados.class);
           entidad = incidenteReppository.save(entidad);
           return Optional.ofNullable(MapperUtils.DtoFromEntity(entidad, IncidentesRegistradosDTO.class));
        }else{
           return null;
        }
    }

    @Override
    @Transactional
    public Optional<IncidentesRegistradosDTO> inactive(Long id) {
        incidenteReppository.Inactivar(id);
        return findById(id);
    }


    @Override
    @Transactional(readOnly = true)
    public Optional<List<IncidentesRegistradosDTO>> findByCategoriaId(Long id) {
        return ServiceConvertionHelper.findList(incidenteReppository.findByCategoria(id), IncidentesRegistradosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<IncidentesRegistradosDTO>> findByAreaTrabajoId(Long id) {
        return ServiceConvertionHelper.findList(incidenteReppository.findByAreaTrabajoId(id), IncidentesRegistradosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<IncidentesRegistradosDTO>> filtro(String nomEmisor, String cedEmisor, String nomResponsable, String cedResponsable, String nomCategoria, String nomArea) {
        return ServiceConvertionHelper.findList(incidenteReppository.filtro(nomEmisor, cedEmisor, nomResponsable, cedResponsable, nomCategoria, nomArea), IncidentesRegistradosDTO.class);
    }

}
