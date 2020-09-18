/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.ServiciosGastos;

/**
 *
 * @author cordo
 */
public interface IServiciosGastosRepository extends JpaRepository<ServiciosGastos, Long>{
    
    public List<ServiciosGastos> findByServiciosId(Long id);
    
    public List<ServiciosGastos> findByEmpresa(String empresa);
    
    public List<ServiciosGastos> findByNumeroContrato(String numeroContrato);
}
