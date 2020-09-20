
package org.una.aeropuerto.repositories;

import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.una.aeropuerto.entities.IncidentesEstados;
import org.una.aeropuerto.entities.IncidentesRegistrados;

/**
 *
 * @author cordo
 */
public interface IIncidentesRegistradosRepository extends JpaRepository<IncidentesRegistrados, Long>{
    
    public List<IncidentesRegistrados> findByCategoria(Long id);
    
    public List<IncidentesRegistrados> findByAreaTrabajoId(Long id);
    
    public List<IncidentesRegistrados> findByAreaTrabajo(Long id);
    
    @Query("Select ir from IncidentesRegistrados ir " +
           "join ir.Empleados e on ir.emisor = e.id and ir.responsable = e.id " +
           "join ir.IncidentesCategorias ic on ir.categoria = ic.id " +
           "join ir.AreasTrabajos at on ir.areaTrabajo = at.id" +
           "where UPPER(e.nombre) like :nomEmisor and UPPER(e.cedula) like :cedEmisor and" +
           "UPPER(e.nombre) like :nomResponsable and UPPER(e.cedula) like :cedResponsable and" +
           "UPPER(ic.nombre) like :nomCategoria and UPPER(at.nombre) like :nomArea")
    public List<IncidentesEstados> filtro(String nomEmisor, String cedEmisor, String nomResponsable, String cedResponsable, String nomCategoria, String nomArea);
}
