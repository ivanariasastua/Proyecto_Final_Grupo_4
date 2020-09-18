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
import org.una.aeropuerto.entities.IncidentesRegistrados;
import org.una.aeropuerto.repositories.IIncidentesRegistradosRepository;

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
    public Optional<List<IncidentesRegistrados>> findAll() {
        return Optional.ofNullable(incidenteReppository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IncidentesRegistrados> findById(Long id) {
        return incidenteReppository.findById(id);
    }

    @Override
    @Transactional
    public IncidentesRegistrados create(IncidentesRegistrados incidentesRegistrados) {
        return incidenteReppository.save(incidentesRegistrados);
    }

    @Override
    @Transactional
    public Optional<IncidentesRegistrados> update(IncidentesRegistrados incidentesRegistrados, Long id) {
        if (incidenteReppository.findById(id).isPresent()) {
            return Optional.ofNullable(incidenteReppository.save(incidentesRegistrados));
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
    public Optional<List<IncidentesRegistrados>> findByCategoriaId(Long id) {
         return Optional.ofNullable(incidenteReppository.findByCategoriaId(id));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<IncidentesRegistrados>> findByAreaTrabajoId(Long id) {
         return Optional.ofNullable(incidenteReppository.findByAreaTrabajoId(id));
    }
}
