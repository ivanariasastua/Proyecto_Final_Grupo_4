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
import org.una.aeropuerto.entities.IncidentesCategorias;
import org.una.aeropuerto.repositories.IIncidentesCategoriasRepository;

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
    public Optional<List<IncidentesCategorias>> findAll() {
        return Optional.ofNullable(incidenteReppository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<IncidentesCategorias> findById(Long id) {
        return incidenteReppository.findById(id);
    }

    @Override
    @Transactional
    public IncidentesCategorias create(IncidentesCategorias incidentesCategorias) {
        return incidenteReppository.save(incidentesCategorias);
    }

    @Override
    @Transactional
    public Optional<IncidentesCategorias> update(IncidentesCategorias incidentesCategorias, Long id) {
        if (incidenteReppository.findById(id).isPresent()) {
            return Optional.ofNullable(incidenteReppository.save(incidentesCategorias));
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
    public Optional<List<IncidentesCategorias>> findByNombre(String nombre) {
        return Optional.ofNullable(incidenteReppository.findByNombre(nombre));
    }
}
