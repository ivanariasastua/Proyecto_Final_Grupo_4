/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.una.aeropuerto.repositories;

import java.util.Date;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.una.aeropuerto.entities.ParametrosSistema;

/**
 *
 * @author cordo
 */
public interface IParametrosSistemaRepository extends JpaRepository<ParametrosSistema, Long>{
    
    public ParametrosSistema findByValor(String valor);
    public ParametrosSistema findByCodigoIndentificador(String codigoIndentificador);
    public List<ParametrosSistema> findByFechaRegistroBetween(Date fecha1, Date fecha2);
    public List<ParametrosSistema> findByFechaModificacionBetween(Date fecha1, Date fecha2);

}
