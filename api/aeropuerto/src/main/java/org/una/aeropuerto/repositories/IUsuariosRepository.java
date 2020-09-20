/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.Usuarios;

/**
 *
 * @author cordo
 */
public interface IUsuariosRepository extends JpaRepository<Usuarios, Long>{
    
    public List<Usuarios> findByRol(Long id);
    
    @Query("Select u from Usuarios u where UPPER(u.rol.nombre) like :rol "+
           "and UPPER(u.empleado.nombre) like :nombre and UPPER(u.empleado.cedula) LIKE :cedula and u.estado =:estado")
    public List<Usuarios> findFilter(String rol, String nombre, String cedula, boolean estado);
    
    @Query("update Usuarios us set us.estado = 0 where us.id = id")
    public void inactivar(Long id);
}
