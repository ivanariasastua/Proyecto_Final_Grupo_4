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
import org.una.aeropuerto.dto.IncidentesRegistradosEstadosDTO;
import org.una.aeropuerto.entities.IncidentesRegistradosEstados;
import org.una.aeropuerto.repositories.IIncidentesRegistradosEstadosRepository;
import org.una.aeropuerto.utils.MapperUtils;
import org.una.aeropuerto.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class IncidentesRegistradosEstadosServiceImplementation implements IIncidentesRegistradosEstadosService{

    @Autowired
    private IIncidentesRegistradosEstadosRepository incidenteRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<IncidentesRegistradosEstadosDTO>> findAll() {
        return ServiceConvertionHelper.findList(incidenteRepository.findAll(), IncidentesRegistradosEstadosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IncidentesRegistradosEstadosDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(incidenteRepository.findById(id), IncidentesRegistradosEstadosDTO.class);
    }

    @Override
    @Transactional
    public IncidentesRegistradosEstadosDTO create(IncidentesRegistradosEstadosDTO incidentesRegistradosEstados) {
        IncidentesRegistradosEstados entidad = MapperUtils.EntityFromDto(incidentesRegistradosEstados, IncidentesRegistradosEstados.class);
        entidad = incidenteRepository.save(entidad);
        return MapperUtils.DtoFromEntity(entidad, IncidentesRegistradosEstadosDTO.class);
    }

    @Override
    @Transactional
    public Optional<IncidentesRegistradosEstadosDTO> update(IncidentesRegistradosEstadosDTO incidentesRegistradosEstados, Long id) {
        if(incidenteRepository.findById(id).isPresent()){
            IncidentesRegistradosEstados entidad = MapperUtils.EntityFromDto(incidentesRegistradosEstados, IncidentesRegistradosEstados.class);
            entidad = incidenteRepository.save(entidad);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(entidad, IncidentesRegistradosEstadosDTO.class));
        }else{
            return null;
        }
    
    }

    @Override
    public Optional<List<IncidentesRegistradosEstadosDTO>> findByIncidentesRegistradosId(Long id) {
        return ServiceConvertionHelper.findList(incidenteRepository.findByIncidenteRegistrado(id), IncidentesRegistradosEstadosDTO.class);
    }
}
