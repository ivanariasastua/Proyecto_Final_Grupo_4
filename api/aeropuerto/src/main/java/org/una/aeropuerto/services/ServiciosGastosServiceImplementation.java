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
import org.una.aeropuerto.entities.ServiciosGastos;
import org.una.aeropuerto.repositories.IServiciosGastosRepository;

/**
 *
 * @author cordo
 */
@Service
public class ServiciosGastosServiceImplementation implements IServiciosGastosService {

    @Autowired
    private IServiciosGastosRepository gastosRepository;

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServiciosGastos>> findAll() {
        return Optional.ofNullable(gastosRepository.findAll());
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<ServiciosGastos> findById(Long id) {
        return gastosRepository.findById(id);
    }

    @Override
    @Transactional
    public ServiciosGastos create(ServiciosGastos gastosMantenimientos) {
        return gastosRepository.save(gastosMantenimientos);
    }

    @Override
    @Transactional
    public Optional<ServiciosGastos> update(ServiciosGastos gastosMantenimientos, Long id) {
        if (gastosRepository.findById(id).isPresent()) {
            return Optional.ofNullable(gastosRepository.save(gastosMantenimientos));
        }
        return null;
    }

    @Override
    @Transactional
    public void delete(Long id) {
        gastosRepository.deleteById(id);
    }

    @Override
    @Transactional
    public void deleteAll() {
        gastosRepository.deleteAll();
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServiciosGastos>> findByServiciosId(Long id) {
        return Optional.ofNullable(gastosRepository.findByServiciosId(id));
    }
    
    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServiciosGastos>> findByEmpresa(String empresa) {
        return Optional.ofNullable(gastosRepository.findByEmpresa(empresa));
    }

    @Override
    @Transactional(readOnly = true)
    public Optional<List<ServiciosGastos>> findByNumeroContrato(String numeroContrato) {
        return Optional.ofNullable(gastosRepository.findByNumeroContrato(numeroContrato));
    }
}
