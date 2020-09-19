/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Transacciones;

/**
 *
 * @author cordo
 */
public interface ITransaccionesRepository  extends JpaRepository<Transacciones, Long>{
    
    public List<Transacciones> findByUsuario(Long id);
    
    public List<Transacciones> findByAccion(String accion);
}
