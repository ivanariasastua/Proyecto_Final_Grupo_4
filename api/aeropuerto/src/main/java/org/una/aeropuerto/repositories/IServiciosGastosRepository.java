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
import org.springframework.data.repository.query.Param;
import org.una.aeropuerto.entities.ServiciosGastos;

/**
 *
 * @author cordo
 */
public interface IServiciosGastosRepository extends JpaRepository<ServiciosGastos, Long>{
    
    @Query("Select sg from ServiciosGastos sg "+
         /*   "join sg.servicio s on sg.id = s.id "+*/
            "where UPPER(sg.servicio.nombre) like CONCAT('%', UPPER(:servicio), '%')")
    public List<ServiciosGastos> findByServicio(@Param("servicio")String servicio);
    
    @Query("select sg from ServiciosGastos sg where UPPER(sg.empresa) like CONCAT('%', UPPER(:empresa), '%')")
    public List<ServiciosGastos> findByEmpresa(String empresa);
    
    @Query("select sg from ServiciosGastos sg where UPPER(sg.numeroContrato) like CONCAT('%', UPPER(:numeroContrato), '%')")
    public List<ServiciosGastos> findByContrato(String numeroContrato);
    
    @Query("SELECT s FROM ServiciosGastos s WHERE UPPER(s.servicio.nombre) like UPPER(:servicio) "+
           "and s.fechaRegistro BETWEEN :fecha and :fecha2 and UPPER(s.empresa) like UPPER(:empresa) " +
           "and s.estadoPago = :estPago and s.estadoGasto = :estGasto and UPPER(s.responsable.cedula) like UPPER(:responsable)")
    public List<ServiciosGastos> findByFechaRegistroServicioEmpresaEstados(@Param("fecha") Date fecha, @Param("fecha2")Date fecha2, 
           @Param("empresa")String empresa, @Param("servicio")String servicio,@Param("estPago")boolean estPago, @Param("estGasto")boolean estGasto, 
           @Param("responsable")String reponsable);
    
    @Query("SELECT s FROM ServiciosGastos s WHERE UPPER(s.servicio.nombre) like UPPER(:servicio) "+
           "and s.fechaRegistro BETWEEN :fecha and :fecha2 and UPPER(s.empresa) like UPPER(:empresa)  "
            + " and UPPER(s.responsable.cedula) like UPPER(:responsable)")
    public List<ServiciosGastos> findByFechaRegistroServicioEmpresa(@Param("fecha") Date fecha, @Param("fecha2")Date fecha2, @Param("empresa")String empresa, 
           @Param("servicio")String servicio, @Param("responsable")String reponsable);
    
    @Query("SELECT s FROM ServiciosGastos s WHERE UPPER(s.servicio.nombre) like UPPER(:servicio) "+
           "and s.fechaRegistro BETWEEN :fecha and :fecha2 and UPPER(s.empresa) like UPPER(:empresa) " +
           "and s.estadoPago = :estPago and UPPER(s.responsable.cedula) like UPPER(:responsable)")
    public List<ServiciosGastos> findByFechaRegistroServicioEmpresaEstadoPago(@Param("fecha") Date fecha, @Param("fecha2")Date fecha2, 
           @Param("empresa")String empresa, @Param("servicio")String servicio, @Param("estPago")boolean estPago, @Param("responsable")String reponsable);
    
    @Query("SELECT s FROM ServiciosGastos s WHERE UPPER(s.servicio.nombre) like UPPER(:servicio) "+
           "and s.fechaRegistro BETWEEN :fecha and :fecha2 and UPPER(s.empresa) like UPPER(:empresa) " +
           "and s.estadoGasto = :estGasto and UPPER(s.responsable.cedula) like UPPER(:responsable)")
    public List<ServiciosGastos> findByFechaRegistroServicioEmpresaEstadoGasto(@Param("fecha") Date fecha, @Param("fecha2")Date fecha2, 
            @Param("empresa")String empresa, @Param("servicio")String servicio, @Param("responsable")String reponsable, @Param("estGasto")boolean estGasto);
}
