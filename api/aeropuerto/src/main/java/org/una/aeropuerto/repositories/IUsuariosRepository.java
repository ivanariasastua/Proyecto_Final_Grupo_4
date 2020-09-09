/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.Usuarios;

/**
 *
 * @author cordo
 */
public interface IUsuariosRepository extends JpaRepository<Usuarios, Long>{
    
    
}
