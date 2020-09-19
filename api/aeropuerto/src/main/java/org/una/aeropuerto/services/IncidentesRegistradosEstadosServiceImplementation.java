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
import org.una.aeropuerto.entities.IncidentesRegistradosEstados;
import org.una.aeropuerto.repositories.IIncidentesRegistradosEstadosRepository;

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
    public Optional<List<IncidentesRegistradosEstados>> findAll() {
        return Optional.ofNullable(incidenteRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IncidentesRegistradosEstados> findById(Long id) {
        return incidenteRepository.findById(id);
    }

    @Override
    @Transactional
    public IncidentesRegistradosEstados create(IncidentesRegistradosEstados incidentesRegistradosEstados) {
        return incidenteRepository.save(incidentesRegistradosEstados);
    }

    @Override
    @Transactional
    public Optional<IncidentesRegistradosEstados> update(IncidentesRegistradosEstados incidentesRegistradosEstados, Long id) {
        if (incidenteRepository.findById(id).isPresent()) {
            return Optional.ofNullable(incidenteRepository.save(incidentesRegistradosEstados));
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        incidenteRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        incidenteRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<IncidentesRegistradosEstados>> findByIncidentesRegistradosId(Long id) {
        return Optional.ofNullable(incidenteRepository.findByIncidenteRegistrado(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<IncidentesRegistradosEstados>> findByIncidentesEstadosId(Long id) {
        return Optional.ofNullable(incidenteRepository.findByIncidenteEstado(id));
    }

}
