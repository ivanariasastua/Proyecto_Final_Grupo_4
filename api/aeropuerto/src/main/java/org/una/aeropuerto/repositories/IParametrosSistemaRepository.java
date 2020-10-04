/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.ParametrosSistema;

/**
 *
 * @author cordo
 */
public interface IParametrosSistemaRepository extends JpaRepository<ParametrosSistema, Long>{
    
    public List<ParametrosSistema> findByValor(String valor);

}
