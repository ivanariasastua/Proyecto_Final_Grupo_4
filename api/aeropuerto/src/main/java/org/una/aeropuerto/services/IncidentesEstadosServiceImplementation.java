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
import org.una.aeropuerto.entities.IncidentesEstados;
import org.una.aeropuerto.repositories.IIncidentesEstadosRepository;

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
    public Optional<List<IncidentesEstados>> findAll() {
        return Optional.ofNullable(incidenteReppository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IncidentesEstados> findById(Long id) {
        return incidenteReppository.findById(id);
    }

    @Override
    @Transactional
    public IncidentesEstados create(IncidentesEstados incidentesEstados) {
        return incidenteReppository.save(incidentesEstados);
    }

    @Override
    @Transactional
    public Optional<IncidentesEstados> update(IncidentesEstados incidentesEstados, Long id) {
        if (incidenteReppository.findById(id).isPresent()) {
            return Optional.ofNullable(incidenteReppository.save(incidentesEstados));
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        incidenteReppository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        incidenteReppository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<IncidentesEstados>> findByEstado(String estado) {
        return Optional.ofNullable(incidenteReppository.findByEstado(estado));
    }
}
