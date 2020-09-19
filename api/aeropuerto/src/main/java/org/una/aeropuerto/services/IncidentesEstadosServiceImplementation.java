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
import org.una.aeropuerto.dto.IncidentesEstadosDTO;
import org.una.aeropuerto.entities.IncidentesEstados;
import org.una.aeropuerto.repositories.IIncidentesEstadosRepository;
import org.una.tramites.utils.MapperUtils;
import org.una.tramites.utils.ServiceConvertionHelper;

/**
 *
 * @author cordo
 */
@Service
public class IncidentesEstadosServiceImplementation implements IIncidentesEstadosService {

    @Autowired
    private IIncidentesEstadosRepository incidenteReppository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<IncidentesEstadosDTO>> findAll() {
        return ServiceConvertionHelper.findList(incidenteReppository.findAll(), IncidentesEstadosDTO.class);
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IncidentesEstadosDTO> findById(Long id) {
        return ServiceConvertionHelper.oneToOptionalDto(incidenteReppository.findById(id), IncidentesEstadosDTO.class);
    }

    @Override
    @Transactional
    public IncidentesEstadosDTO create(IncidentesEstadosDTO incidentesEstados) {
        IncidentesEstados entidad = MapperUtils.EntityFromDto(incidentesEstados, IncidentesEstados.class);
        entidad = incidenteReppository.save(entidad);
        return MapperUtils.DtoFromEntity(entidad, IncidentesEstadosDTO.class);
    }

    @Override
    @Transactional
    public Optional<IncidentesEstadosDTO> update(IncidentesEstadosDTO incidentesEstados, Long id) {
        if(incidenteReppository.findById(id).isPresent()){
            IncidentesEstados entidad = MapperUtils.EntityFromDto(incidentesEstados, IncidentesEstados.class);
            entidad = incidenteReppository.save(entidad);
            return Optional.ofNullable(MapperUtils.DtoFromEntity(entidad, IncidentesEstadosDTO.class));
        }else{
            return null;
        }
    }

    @Override
    @Transactional
    public void inactive(Long id) {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional
    public void inactiveAll() {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<IncidentesEstadosDTO>> findByEstado(String estado) {
        return ServiceConvertionHelper.findList(incidenteReppository.findByEstado(estado), IncidentesEstadosDTO.class);
    }

}
