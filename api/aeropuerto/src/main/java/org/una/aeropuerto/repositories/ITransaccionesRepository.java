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
    
    @Query("select t from Transacciones t where UPPER(t.accion) like CONCAT('%', UPPER(:accion), '%')")
    public List<Transacciones> findByAccion(String accion);

    @Query("Select t from Transacciones t where t.empleado.cedula like :empleado and t.fechaRegistro between :fechaInicio and :fechaFinal")
    public List<Transacciones> findFilter(String empleado, Date fechaInicio, Date fechaFinal);

    public List<Transacciones> findByFechaRegistroBetween(Date fechaInicio, Date fechaFinal);
}
