/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.Transacciones;

/**
 *
 * @author cordo
 */
public interface ITransaccionesRepository  extends JpaRepository<Transacciones, Long>{
    
    public List<Transacciones> findByAccion(String accion);
    
    @Query("update Transacciones t set t.estado = 0 where t.id = id")
    public void inactivar(Long id);
    
    @Query("Select t from Transacciones t where t.empleado.cedula = :empleado and t.fechaRegistro between :fechaInicio and :fechaFinal")
    public List<Transacciones> findFilter(String empleado, Date fechaInicio, Date fechaFinal);
}
