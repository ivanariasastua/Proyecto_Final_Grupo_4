 /*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.ServiciosGastos;

/**
 *
 * @author cordo
 */
public interface IServiciosGastosRepository extends JpaRepository<ServiciosGastos, Long>{
    
    public List<ServiciosGastos> findByServicio(Long id);
    
    
    @Query("Select sg from ServiciosGastos sg join sg.servicio s on sg.servicio = s.id "
            + "where UPPER(sg.empresa) like :nomEmpresa and UPPER(sg.numeroContrato) like :numeroContrato "
            + "and UPPER(s.nombre) like :nomServicio")
    public List<ServiciosGastos> filtro(String nomServicio, String numeroContrato, String nomEmpresa);

    @Query("update ServiciosGastos sg set sg.estado = 0 where sg.id = id")
    public void inactivar(Long id);
}
